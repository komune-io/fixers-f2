// The JS client as a front calls it, against a local F2 server, on the module the compiler emitted.
// Run: node facade.mjs <the compiled f2-client-ktor-http package's kotlin directory>
import assert from "node:assert/strict";
import { createServer } from "node:http";

const dir = process.argv[2];
const { httpF2JsClient } = await import(`${dir}/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs`);
const { F2Exception, F2Refusal } = await import(`${dir}/fixers-f2-f2-dsl-f2-dsl-cqrs.mjs`);

const received = [];
const server = createServer((request, response) => {
  let body = "";
  request.on("data", (chunk) => { body += chunk; });
  request.on("end", () => {
    received.push({ method: request.method, url: request.url, body });
    const answers = {
      "/itemCount": [200, JSON.stringify(JSON.parse(body || "[]").map(({ id }) => ({ id, total: 2 })))],
      "/itemList": [200, '[{"id":"a"},{"id":"b"}]'],
      "/itemDelete": [200, ""],
      "/itemCited": [409, '{"status":409,"refusal":"item.CITED","message":"Cited","citers":["A"]}'],
    };
    const [status, text] = answers[request.url] ?? [404, ""];
    response.writeHead(status, { "content-type": "application/json" });
    response.end(text);
  });
});
await new Promise((resolve) => server.listen(0, "127.0.0.1", resolve));
const client = httpF2JsClient(`http://127.0.0.1:${server.address().port}`);

try {
  const counted = await client.function("itemCount")([{ id: "a" }, { id: "b" }]);
  assert.ok(Array.isArray(counted));
  assert.deepEqual(counted, [{ id: "a", total: 2 }, { id: "b", total: 2 }]);
  assert.deepEqual(received.at(-1), { method: "POST", url: "/itemCount", body: '[{"id":"a"},{"id":"b"}]' });

  assert.deepEqual(await client.supplier("itemList")(), [{ id: "a" }, { id: "b" }]);
  assert.equal(received.at(-1).method, "GET");

  assert.equal(await client.consumer("itemDelete")([{ id: "a" }]), undefined);
  assert.equal(received.at(-1).body, '[{"id":"a"}]');

  const refused = await client.function("itemCited")([{ id: "a" }]).then(() => null, (e) => e);
  assert.ok(refused instanceof F2Exception);
  assert.equal(refused.error.code, 409);
  assert.equal(refused.error.refusal.code, "item.CITED");
  assert.deepEqual([...refused.error.refusal.citers.asJsReadonlyArrayView()], ["A"]);

  const sentBefore = received.length;
  const kotlinObject = await client.function("itemCount")([new F2Refusal("x")]).then(() => null, (e) => e);
  assert.match(String(kotlinObject?.message), /plain object or array/);
  assert.equal(received.length, sentBefore);
  console.log("facade: 5 calls as a front makes them, all as expected");
} finally {
  server.close();
}
