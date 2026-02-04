package f2.client.ktor.http

import f2.client.F2ClientType
import f2.client.ktor.http.model.F2UploadCommand
import f2.client.ktor.http.model.F2UploadSingleCommand
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.full.isSubclassOf

@Serializable
data class ExtTestRequest(val id: String, val value: Int)

@Serializable
data class ExtTestResponse(val result: String)

class HttpF2ClientExtensionTest {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Test
    fun `HttpF2Client is created with correct properties`() {
        val httpClient = HttpClient(Java)
        val json = Json { ignoreUnknownKeys = true }

        val client = HttpF2Client(httpClient, "http://example.com", json)

        assertThat(client.urlBase).isEqualTo("http://example.com")
        assertThat(client.httpClient).isEqualTo(httpClient)
        assertThat(client.json).isEqualTo(json)
        assertThat(client.type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `HttpF2Client uses default json when not specified`() {
        val httpClient = HttpClient(Java)

        val client = HttpF2Client(httpClient, "http://test.com")

        assertThat(client.json).isNotNull()
    }

    @Test
    fun `F2UploadSingleCommand is subclass of F2UploadCommand`() {
        assertThat(F2UploadSingleCommand::class.isSubclassOf(F2UploadCommand::class)).isTrue()
    }

    @Test
    fun `Regular data class is not subclass of F2UploadCommand`() {
        data class RegularCommand(val value: String)
        assertThat(RegularCommand::class.isSubclassOf(F2UploadCommand::class)).isFalse()
    }

    @Test
    suspend fun `function extension with regular type makes single batched request`() {
        var requestCount = 0

        val mockClient = HttpClient(MockEngine { request ->
            requestCount++
            respond(
                content = """[{"result": "ok"}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<ExtTestRequest, ExtTestResponse>("test")

        val results = function.invoke(flowOf(ExtTestRequest("1", 42))).toList()

        // Extension uses List<MSG> typeInfo, resulting in single batched request
        assertThat(requestCount).isEqualTo(1)
        assertThat(results).hasSize(1)
        assertThat(results[0].result).isEqualTo("ok")
    }

    @Test
    suspend fun `function extension with regular type returns unwrapped list items`() {
        val mockClient = HttpClient(MockEngine { request ->
            respond(
                content = """[{"result": "first"}, {"result": "second"}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<ExtTestRequest, ExtTestResponse>("test")

        val results = function.invoke(flowOf(ExtTestRequest("1", 1))).toList()

        // List response items are emitted individually to the flow
        assertThat(results).hasSize(2)
        assertThat(results[0].result).isEqualTo("first")
        assertThat(results[1].result).isEqualTo("second")
    }

    @Test
    suspend fun `function extension batches multiple flow items into single list request`() {
        var requestCount = 0

        val mockClient = HttpClient(MockEngine { request ->
            requestCount++
            // Return a list with 3 responses for the 3 input items
            respond(
                content = """[{"result": "r1"}, {"result": "r2"}, {"result": "r3"}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }) {
            install(ContentNegotiation) {
                json(json)
            }
        }

        val client = HttpF2Client(mockClient, "http://localhost", json)
        val function = client.function<ExtTestRequest, ExtTestResponse>("test")

        val input = flowOf(
            ExtTestRequest("1", 1),
            ExtTestRequest("2", 2),
            ExtTestRequest("3", 3)
        )
        val results = function.invoke(input).toList()

        // Extension function uses List<MSG> typeInfo, so all items are batched into single request
        assertThat(requestCount).isEqualTo(1)
        // Response list items are emitted individually
        assertThat(results).hasSize(3)
        assertThat(results.map { it.result }).containsExactly("r1", "r2", "r3")
    }
}
