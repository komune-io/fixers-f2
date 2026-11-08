package f2.client.ktor.http

import f2.dsl.cqrs.exception.F2Exception
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.typeOf
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HttpF2ClientErrorTest {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private fun f2Client(
        status: HttpStatusCode,
        body: String,
        config: HttpClientConfig<*>.() -> Unit = { install(ContentNegotiation) { json(json) } },
    ): HttpF2Client {
        val engine = MockEngine {
            respond(
                content = body,
                status = status,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        return HttpF2Client(HttpClient(engine, config), "http://localhost", json)
    }

    private fun HttpF2Client.testConsumer() = consumer<TestRequest>(
        route = "consume",
        queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>())
    )

    private fun HttpF2Client.testFunction() = function<TestRequest, TestResponse>(
        route = "process",
        queryTypeInfo = TypeInfo(TestRequest::class, typeOf<TestRequest>()),
        responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
    )

    private fun HttpF2Client.testSupplier() = supplier<TestResponse>(
        route = "list",
        responseTypeInfo = TypeInfo(TestResponse::class, typeOf<TestResponse>())
    )

    private val cancellingOnFirstErrorRead: HttpClientConfig<*>.() -> Unit = {
        install(ContentNegotiation) { json(json) }
        install(
            createClientPlugin("CancelFirstErrorRead") {
                var cancelled = false
                client.responsePipeline.intercept(HttpResponsePipeline.Receive) {
                    if (!this.context.response.status.isSuccess() && !cancelled) {
                        cancelled = true
                        throw CancellationException("caller went away")
                    }
                }
            }
        )
    }

    @Test
    suspend fun `consumer propagates a cancellation raised while the error body is read`() {
        val client = f2Client(HttpStatusCode.InternalServerError, """{"message":"boom"}""", cancellingOnFirstErrorRead)

        val failure = runCatching { client.testConsumer().invoke(flowOf(TestRequest("id", 1))) }.exceptionOrNull()

        assertThat(failure).isInstanceOf(CancellationException::class.java)
            .isNotInstanceOf(F2Exception::class.java)
    }

    @Test
    suspend fun `supplier propagates a cancellation raised while the error body is read`() {
        val client = f2Client(HttpStatusCode.InternalServerError, """{"message":"boom"}""", cancellingOnFirstErrorRead)

        val failure = runCatching { client.testSupplier().invoke().toList() }.exceptionOrNull()

        assertThat(failure).isInstanceOf(CancellationException::class.java)
    }

    @Test
    suspend fun `error body without code carries the HTTP status`() {
        val client = f2Client(HttpStatusCode.Forbidden, """{"message":"Forbidden","id":"err-1"}""")

        val exception = assertThrows<F2Exception> {
            client.testConsumer().invoke(flowOf(TestRequest("id", 1)))
        }

        assertThat(exception.error.code).isEqualTo(403)
        assertThat(exception.error.message).isEqualTo("Forbidden")
        assertThat(exception.error.id).isEqualTo("err-1")
    }

    @Test
    suspend fun `error body with a blank or null code carries the HTTP status`() {
        listOf("\"\"", "\" \"", "null").forEach { code ->
            val client = f2Client(HttpStatusCode.Forbidden, """{"message":"Forbidden","code":$code}""")

            val exception = assertThrows<F2Exception> {
                client.testFunction().invoke(flowOf(TestRequest("id", 1))).toList()
            }

            assertThat(exception.error.code).`as`("code %s", code).isEqualTo(403)
            assertThat(exception.error.message).`as`("code %s", code).isEqualTo("Forbidden")
        }
    }

    @Test
    suspend fun `error body code is kept when the body carries one`() {
        val client = f2Client(HttpStatusCode.BadRequest, """{"message":"Conflict","code":409}""")

        val exception = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(409)
    }

    @Test
    suspend fun `structured error is decoded when the HTTP client has no converter`() {
        val client = f2Client(HttpStatusCode.NotFound, """{"message":"Not found","id":"err-2","code":404}""") {}

        val exception = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(exception.error.message).isEqualTo("Not found")
        assertThat(exception.error.id).isEqualTo("err-2")
        assertThat(exception.error.code).isEqualTo(404)
    }

    @Test
    suspend fun `consumer translates the response exception of a client expecting success`() {
        val client = f2Client(HttpStatusCode.BadRequest, """{"message":"Invalid","code":400}""") {
            install(ContentNegotiation) { json(json) }
            expectSuccess = true
        }

        val exception = assertThrows<F2Exception> {
            client.testConsumer().invoke(flowOf(TestRequest("id", 1)))
        }

        assertThat(exception.error.code).isEqualTo(400)
        assertThat(exception.error.message).isEqualTo("Invalid")
    }

    @Test
    suspend fun `function and supplier translate the response exception of a client expecting success`() {
        val client = f2Client(HttpStatusCode.ServiceUnavailable, "down") {
            install(ContentNegotiation) { json(json) }
            expectSuccess = true
        }

        val fromFunction = assertThrows<F2Exception> {
            client.testFunction().invoke(flowOf(TestRequest("id", 1))).toList()
        }
        val fromSupplier = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(fromFunction.error.code).isEqualTo(503)
        assertThat(fromFunction.error.message).isEqualTo("down")
        assertThat(fromSupplier.error.code).isEqualTo(503)
    }
}
