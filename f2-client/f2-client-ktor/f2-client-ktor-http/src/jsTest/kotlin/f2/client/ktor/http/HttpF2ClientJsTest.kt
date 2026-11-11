package f2.client.ktor.http

import f2.client.ktor.common.applyConfig
import f2.client.ktor.http.model.exported.httpF2JsClient
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.exception.F2Exception
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import kotlin.js.Promise
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue
import kotlinx.coroutines.await
import kotlinx.coroutines.test.runTest
import f2.client.ktor.http.model.exported.HttpF2JsClient as JsClient

/** The client as a JS caller uses it: every function called through its exported names, with JS values. */
class HttpF2ClientJsTest {

    private val requests = mutableListOf<HttpRequestData>()

    private fun client(status: HttpStatusCode = HttpStatusCode.OK, body: String): JsClient = HttpF2JsClient(
        HttpF2Client(
            urlBase = "http://f2.test",
            httpClient = HttpClient(MockEngine { request ->
                requests.add(request)
                respond(body, status, headersOf(HttpHeaders.ContentType, "application/json"))
            }) { applyConfig() },
        )
    ).unsafeCast<JsClient>()

    private fun sent(): String = (requests.single().body as TextContent).text

    private suspend fun call(function: dynamic, messages: dynamic): dynamic =
        function(messages).unsafeCast<Promise<dynamic>>().await()

    @Test
    fun theExportedFactoryBuildsAClientWhoseFunctionsAreJsFunctions() {
        val client = httpF2JsClient("http://f2.test")
        assertEquals("function", jsTypeOf(client.function("itemCount")))
        assertEquals("function", jsTypeOf(client.supplier("itemList")))
        assertEquals("function", jsTypeOf(client.consumer("itemDelete")))
    }

    @Test
    fun aFunctionPostsJsMessagesAndResolvesToJsResults() = runTest {
        val results = call(client(body = """[{"id":"a","total":2}]""").function("itemCount"), js("[{ id: 'a' }]"))

        assertEquals(HttpMethod.Post, requests.single().method)
        assertEquals("http://f2.test/itemCount", requests.single().url.toString())
        assertEquals("""[{"id":"a"}]""", sent())
        assertTrue(js("Array").isArray(results) as Boolean)
        assertEquals(2, results[0].total as Int)
    }

    @Test
    fun aSingleResultResolvesToAnArrayOfIt() = runTest {
        val results = call(client(body = """{"id":"a"}""").function("itemGet"), js("[{ id: 'a' }]"))

        assertEquals(1, results.length as Int)
        assertEquals("a", results[0].id as String)
    }

    @Test
    fun aSupplierGetsAndResolvesToJsResults() = runTest {
        val supplier = client(body = """[{"id":"a"},{"id":"b"}]""").supplier("itemList")

        val results = supplier().unsafeCast<Promise<Array<dynamic>>>().await()

        assertEquals(HttpMethod.Get, requests.single().method)
        assertEquals("a,b", results.map { item: dynamic -> item.id }.joinToString(","))
    }

    @Test
    fun aConsumerPostsJsMessagesAndResolvesToUndefined() = runTest {
        val result = call(client(body = "").consumer("itemDelete"), js("[{ id: 'a' }, { id: 'b' }]"))

        assertEquals("""[{"id":"a"},{"id":"b"}]""", sent())
        assertEquals("undefined", jsTypeOf(result))
    }

    @Test
    fun aRefusalRejectsThePromiseWithTheF2Error() = runTest {
        val body = """{"status":409,"refusal":"item.CITED","message":"Cited","citers":["A"]}"""
        val function = client(HttpStatusCode.Conflict, body).function("itemDelete")

        val failure = runCatching { call(function, js("[{ id: 'a' }]")) }.exceptionOrNull()

        val error = assertIs<F2Exception>(failure).error
        assertEquals(409, error.code)
        assertEquals("item.CITED", error.refusal?.code)
        assertEquals(listOf("A"), error.refusal?.citers)
    }

    @Test
    fun aMessageThatIsAKotlinObjectIsRefusedBeforeAnyRequest() = runTest {
        val function = client(body = "[]").function("itemCount")

        val failure = runCatching { call(function, arrayOf(F2Refusal(code = "x"))) }.exceptionOrNull()

        assertIs<IllegalArgumentException>(failure)
        assertTrue(requests.isEmpty())
    }
}
