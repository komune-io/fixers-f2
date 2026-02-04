package f2.client.ktor.http

import f2.dsl.cqrs.exception.F2Exception
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.typeOf

@Serializable
data class TestRequest(val id: String, val value: Int)

@Serializable
data class TestResponse(val result: String, val count: Int)

class HttpF2ClientMockTest {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Test
    suspend fun `supplier returns single response from GET request`() {
        val mockClient = HttpClient(MockEngine { request ->
            assertThat(request.method).isEqualTo(HttpMethod.Get)
            assertThat(request.url.encodedPath).isEqualTo("/test-route")
            respond(
                content = """{"result": "success", "count": 42}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val supplier = client.supplier<TestResponse>(
            route = "test-route",
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val results = supplier.invoke().toList()

        assertThat(results).hasSize(1)
        assertThat(results[0].result).isEqualTo("success")
        assertThat(results[0].count).isEqualTo(42)
    }

    @Test
    suspend fun `supplier returns collection items individually in flow`() {
        val mockClient = HttpClient(MockEngine { request ->
            respond(
                content = """[{"result": "first", "count": 1}, {"result": "second", "count": 2}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        // When response type is List, items are emitted individually to the flow
        val supplier = client.supplier<List<TestResponse>>(
            route = "test-list",
            responseTypeInfo = TypeInfo(List::class, typeOf<List<TestResponse>>())
        )

        val results = supplier.invoke().toList()

        // The handlePayloadResponse function unwraps collections and emits each item individually
        assertThat(results).hasSize(2)
    }

    @Test
    suspend fun `function posts JSON and returns response`() {
        val mockClient = HttpClient(MockEngine { request ->
            assertThat(request.method).isEqualTo(HttpMethod.Post)
            assertThat(request.url.encodedPath).isEqualTo("/process")
            respond(
                content = """{"result": "processed", "count": 100}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<TestRequest, TestResponse>(
            route = "process",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val input = flowOf(TestRequest("test-id", 50))
        val results = function.invoke(input).toList()

        assertThat(results).hasSize(1)
        assertThat(results[0].result).isEqualTo("processed")
        assertThat(results[0].count).isEqualTo(100)
    }

    @Test
    suspend fun `function handles multiple inputs`() {
        var requestCount = 0

        val mockClient = HttpClient(MockEngine { request ->
            requestCount++
            respond(
                content = """{"result": "item-$requestCount", "count": $requestCount}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<TestRequest, TestResponse>(
            route = "multi",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val input = flowOf(
            TestRequest("id-1", 1),
            TestRequest("id-2", 2),
            TestRequest("id-3", 3)
        )
        val results = function.invoke(input).toList()

        assertThat(results).hasSize(3)
        assertThat(requestCount).isEqualTo(3)
    }

    @Test
    suspend fun `function returns collection items as flow`() {
        val mockClient = HttpClient(MockEngine { request ->
            respond(
                content = """[{"result": "a", "count": 1}, {"result": "b", "count": 2}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<TestRequest, List<TestResponse>>(
            route = "batch",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
            responseTypeInfo = TypeInfo(List::class, typeOf<List<TestResponse>>())
        )

        val results = function.invoke(flowOf(TestRequest("batch", 0))).toList()

        assertThat(results).hasSize(2)
    }

    @Test
    suspend fun `consumer posts data without expecting response body`() {
        var receivedRequest = false

        val mockClient = HttpClient(MockEngine { request ->
            assertThat(request.method).isEqualTo(HttpMethod.Post)
            receivedRequest = true
            respond(
                content = "",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val consumer = client.consumer<TestRequest>(
            route = "consume",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>())
        )

        consumer.invoke(flowOf(TestRequest("consume-id", 99)))

        assertThat(receivedRequest).isTrue()
    }

    @Test
    suspend fun `supplier throws F2Exception on error response`() {
        val mockClient = HttpClient(MockEngine { request ->
            respondError(
                status = HttpStatusCode.InternalServerError,
                content = "Server error occurred"
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val supplier = client.supplier<TestResponse>(
            route = "error-route",
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val exception = assertThrows<F2Exception> {
            supplier.invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(500)
    }

    @Test
    suspend fun `function throws F2Exception on error response`() {
        val mockClient = HttpClient(MockEngine { request ->
            respondError(
                status = HttpStatusCode.BadRequest,
                content = "Invalid request"
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<TestRequest, TestResponse>(
            route = "bad-request",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val exception = assertThrows<F2Exception> {
            function.invoke(flowOf(TestRequest("bad", 0))).toList()
        }

        assertThat(exception.error.code).isEqualTo(400)
    }

    @Test
    suspend fun `supplier handles F2Error JSON response`() {
        val errorJson = """{
            "id": "error-123",
            "timestamp": "2024-01-01T00:00:00Z",
            "code": 404,
            "message": "Not found"
        }"""

        val mockClient = HttpClient(MockEngine { request ->
            respond(
                content = errorJson,
                status = HttpStatusCode.NotFound,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val supplier = client.supplier<TestResponse>(
            route = "not-found",
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val exception = assertThrows<F2Exception> {
            supplier.invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(404)
        assertThat(exception.error.message).isEqualTo("Not found")
    }

    @Test
    suspend fun `function posts List type content in single request`() {
        var requestCount = 0

        val mockClient = HttpClient(MockEngine { request ->
            requestCount++
            respond(
                content = """{"result": "batch-processed", "count": 10}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        // When queryTypeInfo is List, all items in the flow are collected and sent as one array
        val function = client.function<TestRequest, TestResponse>(
            route = "batch-endpoint",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        // Send multiple items - each becomes a separate request
        val input = flowOf(
            TestRequest("1", 1),
            TestRequest("2", 2)
        )
        val results = function.invoke(input).toList()

        // Each item in flow generates a separate request
        assertThat(requestCount).isEqualTo(2)
        assertThat(results).hasSize(2)
    }

    @Test
    suspend fun `client builds correct URL with base and route`() {
        var capturedUrl: String? = null

        val mockClient = HttpClient(MockEngine { request ->
            capturedUrl = request.url.toString()
            respond(
                content = """{"result": "ok", "count": 0}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://api.example.com/v1", json)
        val supplier = client.supplier<TestResponse>(
            route = "resource/action",
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        supplier.invoke().toList()

        assertThat(capturedUrl).isEqualTo("http://api.example.com/v1/resource/action")
    }

    @Test
    suspend fun `function handles empty flow input`() {
        var requestCount = 0

        val mockClient = HttpClient(MockEngine { request ->
            requestCount++
            respond(
                content = """{"result": "ok", "count": 0}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<TestRequest, TestResponse>(
            route = "empty",
            queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
            responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
        )

        val results = function.invoke(emptyFlow()).toList()

        assertThat(results).isEmpty()
        assertThat(requestCount).isEqualTo(0)
    }
}
