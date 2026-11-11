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
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.cancel
import io.ktor.utils.io.writeStringUtf8
import kotlin.coroutines.cancellation.CancellationException
import kotlin.js.Promise
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.await
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
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
        function.invoke(messages).unsafeCast<Promise<dynamic>>().await()

    // What the promise rejects with: a JS value, never a Kotlin exception.
    private suspend fun rejection(function: dynamic, messages: dynamic): dynamic =
        settled(function.invoke(messages).unsafeCast<Promise<dynamic>>()).await()

    // The reason a promise rejects with, or null when it resolves.
    private fun settled(promise: Promise<dynamic>): Promise<dynamic> =
        promise.then<dynamic>({ _ -> null }, { reason -> reason })

    @Test
    fun theExportedFactoryBuildsAClientWhoseFunctionsHaveAnInvokeMethod() {
        val client = httpF2JsClient("http://f2.test")
        assertEquals("function", jsTypeOf(client.function("itemCount").invoke))
        assertEquals("function", jsTypeOf(client.supplier("itemList").invoke))
        assertEquals("function", jsTypeOf(client.consumer("itemDelete").invoke))
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

        val results = supplier.invoke().unsafeCast<Promise<Array<dynamic>>>().await()

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
    fun anErrorStatusWithAJsonErrorObjectRejectsAsF2() = runTest {
        val body = """{"status":500,"error":"Internal Server Error"}"""
        val failure = rejection(client(HttpStatusCode.InternalServerError, body).function("itemCount"), js("[]"))

        assertEquals("F2", failure.kind as String)
        assertEquals(500, failure.status as Int)
        assertEquals("Internal Server Error", failure.error.message as String)
    }

    @Test
    fun anErrorStatusWithATextBodyRejectsAsHttpWithTheText() = runTest {
        val failure = rejection(client(HttpStatusCode.InternalServerError, "boom").function("itemCount"), js("[]"))

        assertEquals("HTTP", failure.kind as String)
        assertEquals(500, failure.status as Int)
        assertEquals("boom", failure.body as String)
        assertEquals(null, failure.error)
    }

    @Test
    fun anErrorStatusWithAnEmptyBodyRejectsAsHttpWithItsStatus() = runTest {
        val failure = rejection(client(HttpStatusCode.NotFound, "").function("itemCount"), js("[]"))

        assertEquals("HTTP", failure.kind as String)
        assertEquals(404, failure.status as Int)
        assertEquals("", failure.body as String)
        assertEquals("HTTP 404", failure.message as String)
    }

    @Test
    fun theF2ErrorJsonHoldsEveryFieldOfTheKotlinError() = runTest {
        val body = """{"refusal":"item.CITED","message":"Cited","parameters":{"id":"i-1"},"citers":["A"],""" +
            """"id":"e-1","requestId":"r-1","timestamp":"t","code":409}"""
        val json = rejection(client(HttpStatusCode.Conflict, body).function("itemDelete"), js("[]")).error

        // Every getter the Kotlin classes export is a key of the JSON: a field added to the DTO fails here.
        val error = f2.dsl.cqrs.error.F2Error(message = "m", refusal = F2Refusal(code = "c"))
        assertEquals(exportedGetters(error), keys(json))
        assertEquals(exportedGetters(error.refusal), keys(json.refusal))
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
    fun aBodyCutWhileReadRejectsAsNetwork() = runTest {
        val client = HttpF2JsClient(
            HttpF2Client(
                urlBase = "http://f2.test",
                httpClient = HttpClient(MockEngine {
                    val body = ByteChannel(autoFlush = true)
                    body.writeStringUtf8("[{\"id\"")
                    body.cancel(IOException("cut"))
                    respond(body, HttpStatusCode.OK, headersOf(HttpHeaders.ContentType, "application/json"))
                }) { applyConfig() },
            )
        ).unsafeCast<JsClient>()

        assertEquals("NETWORK", rejection(client.function("itemCount"), js("[]")).kind as String)
    }

    @Test
    fun aCancellationNeitherAbortNorCloseRejectsAsNetwork() = runTest {
        val failure = rejection(
            client(body = "[]") { throw CancellationException("timed out") }.function("itemCount"),
            js("[]"),
        )

        assertEquals("NETWORK", failure.kind as String)
    }

    @Test
    fun aCyclicMessageIsRefusedBeforeAnyRequest() = runTest {
        val message: dynamic = js("({ id: 'a' })")
        message.self = message

        val failure = rejection(client(body = "[]").function("itemCount"), arrayOf(message))

        assertTrue(js("TypeError").prototype.isPrototypeOf(failure) as Boolean)
        assertTrue((failure.message as String).contains("cycle"))
        assertTrue(requests.isEmpty())
    }

    @Test
    fun aSharedButAcyclicValueIsSent() = runTest {
        val shared: dynamic = js("({ id: 'a' })")

        call(client(body = "[]").function("itemCount"), arrayOf(shared, shared))

        assertEquals("""[{"id":"a"},{"id":"a"}]""", sent())
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
    fun aNonFiniteNumberAtAnyDepthIsRefusedBeforeAnyRequest() = runTest {
        listOf(js("[{ total: NaN }]"), js("[{ page: { totals: [Infinity] } }]"), js("[-Infinity]")).forEach { messages ->
            val failure = rejection(client(body = "[]").function("itemCount"), messages)

            assertTrue(js("TypeError").prototype.isPrototypeOf(failure) as Boolean)
            assertTrue((failure.message as String).contains("finite"))
        }
        assertTrue(requests.isEmpty())
    }

    @Test
    fun whatIsCheckedIsWhatIsSentAGetterReadOnce() = runTest {
        val reads = mutableListOf<String>()
        val message: dynamic = js("({})")
        val descriptor: dynamic = js("({ enumerable: true })")
        descriptor.get = { reads.add("id"); if (reads.size == 1) "a" else undefined }
        js("Object").defineProperty(message, "id", descriptor)

        call(client(body = "[]").function("itemCount"), arrayOf(message))

        assertEquals("""[{"id":"a"}]""", sent())
        assertEquals(listOf("id"), reads)
    }

    @Test
    fun aClosedClientRejectsAsClosedEvenWhenTheSignalIsAlreadyAborted() = runTest {
        val client = client(body = "[]")
        val controller: dynamic = js("new AbortController()")
        controller.abort()
        client.close()

        val failure = settled(client.function("itemCount").invoke(js("[]"), controller.signal).unsafeCast<Promise<dynamic>>())

        assertEquals("CLOSED", failure.await().kind as String)
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
            .function("itemCount")

        val pending = settled(function.invoke(js("[]"), controller.signal).unsafeCast<Promise<dynamic>>())
        started.await()
        controller.abort()

        assertEquals("AbortError", pending.await().name as String)
    }

    @Test
    fun theAbortListenerIsAddedOnceAndRemovedWhenTheCallEnds() = runTest {
        val listeners = mutableListOf<String>()
        val signal: dynamic = js("({ aborted: false })")
        signal.addEventListener = { type: String, listener: dynamic, options: dynamic ->
            listeners.add("add $type once=${options.once}")
            signal.listener = listener
        }
        signal.removeEventListener = { type: String, listener: dynamic ->
            listeners.add("remove $type same=${listener === signal.listener}")
        }

        call2(client(body = "[]").function("itemCount"), js("[]"), signal)

        assertEquals(listOf("add abort once=true", "remove abort same=true"), listeners)
    }

    @Test
    fun closingTheClientRejectsItsPendingAndLaterCallsAsClosed() = runTest {
        val started = CompletableDeferred<Unit>()
        val client = client(body = "[]") { started.complete(Unit); CompletableDeferred<Unit>().await() }
        val pending = settled(client.function("itemCount").invoke(js("[]")).unsafeCast<Promise<dynamic>>())
        started.await()

        client.close()

        assertEquals("CLOSED", pending.await().kind as String)
        assertEquals("CLOSED", rejection(client.function("itemCount"), js("[]")).kind as String)
    }

    @Test
    fun aPromiseSettlesOnceWhateverEndsTheCall() {
        val outcomes = mutableListOf<String>()
        val settle = Settle<Int>({ value -> outcomes.add("resolve $value") }, { _ -> outcomes.add("reject") })

        settle.reject(js("new Error()"))
        settle.reject(js("new Error()"))

        assertEquals(listOf("reject"), outcomes)
    }

    private fun exportedGetters(value: dynamic): List<String> {
        val descriptors: dynamic = js("Object").getOwnPropertyDescriptors(js("Object").getPrototypeOf(value))
        return js("Object").keys(descriptors).unsafeCast<Array<String>>()
            .filter { name -> descriptors[name].get != undefined }
            .sorted()
    }

    private fun keys(value: dynamic): List<String> = js("Object").keys(value).unsafeCast<Array<String>>().sorted()

    private suspend fun call2(function: dynamic, messages: dynamic, signal: dynamic): dynamic =
        function.invoke(messages, signal).unsafeCast<Promise<dynamic>>().await()
}
