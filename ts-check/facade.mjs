// The JS client as a front calls it, against a local F2 server, on the module the compiler emitted.
// Run: node facade.mjs <the compiled f2-client-ktor-http distribution directory>
// First, every value use.mts imports must exist in the module it names: tsc reads the .d.mts of a package, which
// declares its whole program, while the package's .mjs exports only its own declarations.
import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import { createServer } from "node:http";

const dir = process.argv[2];
const use = readFileSync(new URL("use.mts", import.meta.url), "utf8");
// Every import statement, on one line or several; `import type` names types only and is left out.
const statements = [...use.matchAll(/^import\s[^;]*;/gm)].map(([statement]) => statement);
const valueImports = statements.filter((statement) => !/^import\s+type\s/.test(statement));
assert.ok(valueImports.length > 0, "use.mts imports no value");
for (const statement of valueImports) {
  const parsed = statement.match(/^import\s*\{([^}]*)\}\s*from\s*"[^"]*\/([^/"]+\.mjs)"\s*;$/);
  assert.ok(parsed, `use.mts imports in a form this check does not read: ${statement}`);
  const [, names, file] = parsed;
  const module = await import(`${dir}/${file}`);
  for (const name of names.split(",").map((n) => n.trim().split(/\s+as\s+/)[0]).filter(Boolean)) {
    assert.ok(name in module, `use.mts imports ${name} from ${file}, which does not export it`);
  }
}

const { httpF2JsClient } = await import(`${dir}/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs`);
const { F2Refusal } = await import(`${dir}/fixers-f2-f2-dsl-f2-dsl-cqrs.mjs`);

const received = [];
const server = createServer((request, response) => {
  let body = "";
  request.on("data", (chunk) => { body += chunk; });
  request.on("end", () => {
    received.push({ method: request.method, url: request.url, body });
    if (request.url === "/itemSlow") return; // never answers: aborted or closed by the caller
    if (request.url === "/itemCut") { // a 200 whose body breaks off
      response.writeHead(200, { "content-type": "application/json", "content-length": "100" });
      response.write('[{"id"');
      setTimeout(() => response.socket.destroy(), 20);
      return;
    }
    const answers = {
      "/itemCount": [200, JSON.stringify(JSON.parse(body || "[]").map(({ id }) => ({ id, total: 2 })))],
      "/itemList": [200, '[{"id":"a"},{"id":"b"}]'],
      "/itemDelete": [200, ""],
      "/itemCited": [409, '{"status":409,"refusal":"item.CITED","message":"Cited","parameters":{"id":"i-1"},"citers":["A"]}'],
      "/itemBroken": [500, "boom"],
      "/itemSpring": [500, '{"status":500,"error":"Internal Server Error"}'],
      "/itemHtml": [200, "<html>"],
    };
    const [status, text] = answers[request.url] ?? [404, ""];
    response.writeHead(status, { "content-type": "application/json" });
    response.end(text);
  });
});
await new Promise((resolve) => server.listen(0, "127.0.0.1", resolve));
const client = httpF2JsClient(`http://127.0.0.1:${server.address().port}`);
const rejection = (promise) => promise.then(() => assert.fail("resolved"), (e) => e);

try {
  const counted = await client.function("itemCount")([{ id: "a" }, { id: "b" }]);
  assert.deepEqual(counted, [{ id: "a", total: 2 }, { id: "b", total: 2 }]);
  assert.deepEqual(received.at(-1), { method: "POST", url: "/itemCount", body: '[{"id":"a"},{"id":"b"}]' });
  assert.deepEqual(await client.supplier("itemList")(), [{ id: "a" }, { id: "b" }]);
  assert.equal(received.at(-1).method, "GET");
  assert.equal(await client.consumer("itemDelete")([{ id: "a" }]), undefined);

  // F2: an error status, its body as the F2 error, in JSON.
  const refused = await rejection(client.function("itemCited")([{ id: "a" }]));
  assert.ok(refused instanceof Error);
  assert.deepEqual([refused.kind, refused.status, refused.message], ["F2", 409, "Cited"]);
  assert.deepEqual(refused.error.refusal, { code: "item.CITED", parameters: { id: "i-1" }, citers: ["A"] });
  const spring = await rejection(client.function("itemSpring")([]));
  assert.deepEqual([spring.kind, spring.status, spring.error.message], ["F2", 500, "Internal Server Error"]);
  // HTTP: an error status whose body is text or empty.
  const broken = await rejection(client.function("itemBroken")([]));
  assert.deepEqual([broken.kind, broken.status, broken.body, broken.error], ["HTTP", 500, "boom", null]);
  const missing = await rejection(client.function("itemMissing")([]));
  assert.deepEqual([missing.kind, missing.status, missing.body], ["HTTP", 404, ""]);
  // NETWORK: a body cut while read.
  assert.equal((await rejection(client.function("itemCut")([]))).kind, "NETWORK");
  // UNREADABLE: a success whose body is no JSON.
  const html = await rejection(client.function("itemHtml")([]));
  assert.deepEqual([html.kind, html.status], ["UNREADABLE", 200]);

  // Refused before any request: messages that are no array, a Kotlin object at any depth.
  const sentBefore = received.length;
  assert.ok((await rejection(client.function("itemCount")({ id: "a" }))) instanceof TypeError);
  assert.ok((await rejection(client.function("itemCount")([{ page: { items: [new F2Refusal("x")] } }]))) instanceof TypeError);
  const cyclic = { id: "a" };
  cyclic.self = cyclic;
  assert.match((await rejection(client.function("itemCount")([cyclic]))).message, /cycle/);
  assert.equal(received.length, sentBefore);

  // Aborted: rejects with the signal's reason, as fetch does.
  const controller = new AbortController();
  const slow = rejection(client.function("itemSlow")([], controller.signal));
  while (received.at(-1)?.url !== "/itemSlow") await new Promise((resolve) => setTimeout(resolve, 5));
  controller.abort();
  assert.equal((await slow).name, "AbortError");
} finally {
  server.close();
  server.closeAllConnections();
}

// NETWORK: no server at the URL any more.
const gone = await rejection(client.function("itemCount")([]));
assert.deepEqual([gone.kind, gone.status], ["NETWORK", null]);
// CLOSED: the client closed, its later calls refused.
client.close();
assert.equal((await rejection(client.supplier("itemList")())).kind, "CLOSED");
console.log("facade: every value use.mts imports exists; 15 calls as a front makes them, all as expected");
