package f2.client.ktor.http

import f2.client.ktor.common.applyConfig
import f2.client.ktor.http.model.exported.httpF2JsClient
import f2.dsl.cqrs.error.F2Refusal
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
import kotlin.test.assertTrue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.await
import kotlinx.coroutines.test.runTest
import f2.client.ktor.http.model.exported.HttpF2JsClient as JsClient

/** The client as a JS caller uses it: every function called through its exported names, with JS values. */
class HttpF2ClientJsTest {

    private val requests = mutableListOf<HttpRequestData>()

    private fun client(
        status: HttpStatusCode = HttpStatusCode.OK,
        body: String,
        answer: suspend () -> Unit = {},
    ): JsClient = HttpF2JsClient(
        HttpF2Client(
            urlBase = "http://f2.test",
            httpClient = HttpClient(MockEngine { request ->
                requests.add(request)
                answer()
                respond(body, status, headersOf(HttpHeaders.ContentType, "application/json"))
            }) { applyConfig() },
        )
    ).unsafeCast<JsClient>()

    private fun sent(): String = (requests.single().body as TextContent).text

    private suspend fun call(function: dynamic, messages: dynamic): dynamic =
        function(messages).unsafeCast<Promise<dynamic>>().await()

    // What the promise rejects with: a JS value, never a Kotlin exception.
    private suspend fun rejection(function: dynamic, messages: dynamic): dynamic =
        settled(function(messages).unsafeCast<Promise<dynamic>>()).await()

    // The reason a promise rejects with, or null when it resolves.
    private fun settled(promise: Promise<dynamic>): Promise<dynamic> =
        promise.then<dynamic>({ _ -> null }, { reason -> reason })

    @Test
    fun theExportedFactoryBuildsAClientWhoseFunctionsAreJsFunctions() {
        val client = httpF2JsClient("http://f2.test")
        assertEquals("function", jsTypeOf(client.function("itemCount")))
        assertEquals("function", jsTypeOf(client.supplier("itemList")))
        assertEquals("function", jsTypeOf(client.consumer("itemDelete")))
        client.close()
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
    fun aRefusalRejectsWithAnErrorCarryingTheF2ErrorAsJson() = runTest {
        val body = """{"status":409,"refusal":"item.CITED","message":"Cited",""" +
            """"parameters":{"id":"i-1"},"citers":["A"]}"""

        val failure = rejection(client(HttpStatusCode.Conflict, body).function("itemDelete"), js("[{ id: 'a' }]"))

        assertTrue(js("Error").prototype.isPrototypeOf(failure) as Boolean)
        assertEquals("F2", failure.kind as String)
        assertEquals(409, failure.status as Int)
        assertEquals("Cited", failure.message as String)
        assertEquals("item.CITED", failure.error.refusal.code as String)
        assertEquals("i-1", failure.error.refusal.parameters.id as String)
        assertTrue(js("Array").isArray(failure.error.refusal.citers) as Boolean)
        val refusal = """{"code":"item.CITED","parameters":{"id":"i-1"},"citers":["A"]}"""
        assertEquals(refusal, JSON.stringify(failure.error.refusal))
    }

    @Test
    fun anErrorStatusWithATextBodyRejectsAsF2WithTheTextAsMessage() = runTest {
        val failure = rejection(client(HttpStatusCode.InternalServerError, "boom").function("itemCount"), js("[]"))

        assertEquals("F2", failure.kind as String)
        assertEquals(500, failure.status as Int)
        assertEquals("boom", failure.error.message as String)
        assertEquals(null, failure.error.refusal)
    }

    @Test
    fun anErrorStatusWithAnEmptyBodyRejectsAsF2WithItsStatus() = runTest {
        val failure = rejection(client(HttpStatusCode.NotFound, "").function("itemCount"), js("[]"))

        assertEquals("F2", failure.kind as String)
        assertEquals(404, failure.error.code as Int)
        assertEquals("", failure.error.message as String)
    }

    @Test
    fun aSuccessWhoseBodyIsNoJsonRejectsAsUnreadable() = runTest {
        val failure = rejection(client(body = "<html>").function("itemCount"), js("[]"))

        assertEquals("UNREADABLE", failure.kind as String)
        assertEquals(200, failure.status as Int)
    }

    @Test
    fun noResponseRejectsAsNetwork() = runTest {
        val failure = rejection(client(body = "[]") { error("connection reset") }.function("itemCount"), js("[]"))

        assertEquals("NETWORK", failure.kind as String)
        assertEquals(null, failure.status)
    }

    @Test
    fun aKotlinObjectAtAnyDepthIsRefusedBeforeAnyRequest() = runTest {
        val function = client(body = "[]").function("itemCount")
        val nested: dynamic = js("({ page: { items: [] } })")
        nested.page.items = arrayOf(F2Refusal(code = "x"))

        val failure = rejection(function, arrayOf(nested))

        assertTrue(js("TypeError").prototype.isPrototypeOf(failure) as Boolean)
        assertTrue(requests.isEmpty())
    }

    @Test
    fun messagesThatAreNoArrayAreRefusedBeforeAnyRequest() = runTest {
        val failure = rejection(client(body = "[]").function("itemCount"), js("({ id: 'a' })"))

        assertTrue(js("TypeError").prototype.isPrototypeOf(failure) as Boolean)
        assertTrue(requests.isEmpty())
    }

    @Test
    fun anAbortedCallRejectsWithTheSignalsReason() = runTest {
        val started = CompletableDeferred<Unit>()
        val controller: dynamic = js("new AbortController()")
        val function = client(body = "[]") { started.complete(Unit); CompletableDeferred<Unit>().await() }
            .function("itemCount", controller.signal)

        val pending = settled(function(js("[]")).unsafeCast<Promise<dynamic>>())
        started.await()
        controller.abort()

        assertEquals("AbortError", pending.await().name as String)
    }

    @Test
    fun closingTheClientRejectsItsPendingAndLaterCallsAsClosed() = runTest {
        val started = CompletableDeferred<Unit>()
        val client = client(body = "[]") { started.complete(Unit); CompletableDeferred<Unit>().await() }
        val pending = settled(client.function("itemCount")(js("[]")).unsafeCast<Promise<dynamic>>())
        started.await()

        client.close()

        assertEquals("CLOSED", pending.await().kind as String)
        assertEquals("CLOSED", rejection(client.function("itemCount"), js("[]")).kind as String)
    }
}
