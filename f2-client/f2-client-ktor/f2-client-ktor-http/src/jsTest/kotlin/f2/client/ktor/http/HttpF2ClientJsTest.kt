package f2.client.ktor.http

import f2.client.ktor.common.applyConfig
import f2.client.ktor.http.model.exported.httpClientBuilderDefault
import f2.client.ktor.http.model.exported.httpF2Consumer
import f2.client.ktor.http.model.exported.httpF2Function
import f2.client.ktor.http.model.exported.httpF2Supplier
import f2.client.ktor.http.model.exported.invokeF2Consumer
import f2.client.ktor.http.model.exported.invokeF2Function
import f2.client.ktor.http.model.exported.invokeF2Supplier
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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue
import kotlinx.coroutines.await
import kotlinx.coroutines.test.runTest

/** The client as a JS caller uses it: every function called through its exported name, with JS values. */
class HttpF2ClientJsTest {

	private val requests = mutableListOf<HttpRequestData>()

	private fun client(status: HttpStatusCode = HttpStatusCode.OK, body: String) = HttpF2Client(
		urlBase = "http://f2.test",
		httpClient = HttpClient(MockEngine { request ->
			requests.add(request)
			respond(body, status, headersOf(HttpHeaders.ContentType, "application/json"))
		}) { applyConfig() },
	)

	private fun sent(): String = (requests.single().body as TextContent).text

	@Test
	fun theExportedFactoryBuildsAClientOfTheUrl() {
		val client = httpClientBuilderDefault().build("http://f2.test")
		assertEquals("http://f2.test", client.urlBase as String)
		assertEquals("HTTP", client.type.toString())
	}

	@Test
	fun aFunctionPostsJsMessagesAndResolvesToJsResults() = runTest {
		val function = httpF2Function(client(body = """[{"id":"a","total":2}]"""), "itemCount")

		val results = invokeF2Function(function, js("[{ id: 'a' }]")).await()

		assertEquals(HttpMethod.Post, requests.single().method)
		assertEquals("http://f2.test/itemCount", requests.single().url.toString())
		assertEquals("""[{"id":"a"}]""", sent())
		assertTrue(js("Array").isArray(results) as Boolean)
		assertEquals(2, results[0].total as Int)
	}

	@Test
	fun aSupplierGetsAndResolvesToJsResults() = runTest {
		val supplier = httpF2Supplier(client(body = """[{"id":"a"},{"id":"b"}]"""), "itemList")

		val results = invokeF2Supplier(supplier).await()

		assertEquals(HttpMethod.Get, requests.single().method)
		assertEquals("a,b", results.map { item: dynamic -> item.id }.join(",") as String)
	}

	@Test
	fun aConsumerPostsJsMessages() = runTest {
		invokeF2Consumer(httpF2Consumer(client(body = ""), "itemDelete"), js("[{ id: 'a' }, { id: 'b' }]")).await()

		assertEquals("""[{"id":"a"},{"id":"b"}]""", sent())
	}

	@Test
	fun aRefusalRejectsThePromiseWithTheF2Error() = runTest {
		val body = """{"status":409,"refusal":"item.CITED","message":"Cited","citers":["A"]}"""
		val function = httpF2Function(client(HttpStatusCode.Conflict, body), "itemDelete")

		val failure = runCatching { invokeF2Function(function, js("[{ id: 'a' }]")).await() }.exceptionOrNull()

		val error = assertIs<F2Exception>(failure).error
		assertEquals(409, error.code)
		assertEquals("item.CITED", error.refusal?.code)
		assertEquals(listOf("A"), error.refusal?.citers)
	}
}
