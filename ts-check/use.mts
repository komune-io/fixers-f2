// The JS client as a front types it, against the committed contract kt2Ts made from the compiler's output:
// a function is a JS function of JSON messages to a promise of JSON results. Each @ts-expect-error is a misuse
// tsc must refuse; an unused one fails the check too.
import { F2Exception, Json, PageQuery, PageQueryResult, httpF2JsClient } from "../ts/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http/kotlin/fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs";

const client = httpF2JsClient("http://f2.test");
const page = client.function<PageQuery, PageQueryResult<string>>("itemPage");
const list = client.supplier<PageQueryResult<string>>("itemList");
const remove = client.consumer<PageQuery>("itemDelete");

const results: Json<PageQueryResult<string>>[] = await page([{ pagination: { offset: 0, limit: 10 } }]);
const count: number = results[0].items.length + results[0].total;
const absent = await page([{}]);
const listed: Json<PageQueryResult<string>>[] = await list();
const done: void = await remove([{ pagination: null }]);
const reason: string | undefined = await page([]).then(() => undefined, (e: unknown) =>
  e instanceof F2Exception ? e.error.refusal?.parameters.asJsReadonlyMapView().get("id") : undefined);

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

export { count, absent, listed, done, reason, badMessage, notArray, view, kotlin, answered, supplied };
