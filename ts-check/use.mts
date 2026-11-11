// The JS client as a front types it, against the committed contract kt2Ts made from the compiler's output:
// a function is a JS function of JSON messages to a promise of JSON results, and a failure is JSON too. Each
// expected error below is a misuse tsc must refuse; an unused one fails the check too. Values are imported apart
// from types: facade.mjs checks that the module named exports each of them at run time.
import {
  httpF2JsClient,
} from "../ts/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http/kotlin/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs";
import type { HttpF2JsFailure, Json, PageQuery, PageQueryResult } from "../ts/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http/kotlin/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs";

const client = httpF2JsClient("http://f2.test");
const page = client.function<PageQuery, PageQueryResult<string>>("itemPage");
const list = client.supplier<PageQueryResult<string>>("itemList");
const remove = client.consumer<PageQuery>("itemDelete");

const results: Json<PageQueryResult<string>>[] = await page([{ pagination: { offset: 0, limit: 10 } }]);
const count: number = results[0].items.length + results[0].total;
const absent = await page([{}]);
const listed: Json<PageQueryResult<string>>[] = await list();
const done: void = await remove([{ pagination: null }]);
const signal = new AbortController().signal;
const aborted = await page([], signal);
const listedAborted = await list(signal);
const removedAborted: void = await remove([], signal);
const reason: string | undefined = await page([]).then(() => undefined, (e: unknown) => {
  const failure = e as Json<HttpF2JsFailure>;
  if (failure.kind === "F2") return failure.error?.refusal?.parameters["id"] ?? failure.error?.message;
  return failure.kind === "HTTP" ? failure.body ?? undefined : failure.message;
});
client.close();

// @ts-expect-error a message is JSON: a pagination is an object, not a string
const badMessage = await page([{ pagination: "first" }]);
// @ts-expect-error a message is an array of messages, not one message
const notArray = await page({ pagination: null });
// @ts-expect-error the results are JSON: items is an array, not a Kotlin list
const view = results[0].items.asJsReadonlyArrayView();
// @ts-expect-error the results are JSON, not the Kotlin object
const kotlin: PageQueryResult<string> = results[0];
// @ts-expect-error a consumer answers nothing
const answered: number = await remove([]);
// @ts-expect-error a supplier takes no message
const supplied = await list([{}]);
// @ts-expect-error a failure's kind is one of five names
const unknownKind: Json<HttpF2JsFailure>["kind"] = "TIMEOUT";
// @ts-expect-error a refusal's citers are an array on the wire, not a Kotlin list
const citers = ({} as Json<HttpF2JsFailure>).error?.refusal?.citers.asJsReadonlyArrayView();
// @ts-expect-error without its type arguments a function takes any message, and its results are unknown
const untyped = (await client.function("itemPage")([1, "a"]))[0].id;

// @ts-expect-error a signal is an AbortSignal
const badSignal = await page([], "abort");
// @ts-expect-error a supplier's one argument is its signal
const twoArgs = await list([], signal);

export { badSignal, twoArgs, aborted, listedAborted, removedAborted, untyped, count, absent, listed, done, reason, badMessage, notArray, view, kotlin, answered, supplied, unknownKind, citers };
