package f2.client.ktor.http

import f2.dsl.cqrs.exception.F2Exception
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.typeOf
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HttpF2ClientErrorTest {

    private fun f2Client(
        status: HttpStatusCode,
        body: String,
        config: HttpClientConfig<*>.() -> Unit = { mockContentNegotiation() },
    ): HttpF2Client = f2Client(status, body.encodeToByteArray(), config)

    private fun f2Client(
        status: HttpStatusCode,
        body: ByteArray,
        config: HttpClientConfig<*>.() -> Unit = { mockContentNegotiation() },
        contentType: String = "application/json; charset=UTF-8",
    ): HttpF2Client = mockF2Client(config = config) {
        respond(
            content = body,
            status = status,
            headers = headersOf(HttpHeaders.ContentType, contentType)
        )
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

    private fun failingOnFirstErrorRead(failure: () -> Throwable): HttpClientConfig<*>.() -> Unit = {
        mockContentNegotiation()
        install(
            createClientPlugin("FailFirstErrorRead") {
                var failed = false
                client.responsePipeline.intercept(HttpResponsePipeline.Receive) {
                    if (!this.context.response.status.isSuccess() && !failed) {
                        failed = true
                        throw failure()
                    }
                }
            }
        )
    }

    private val expectingSuccess: HttpClientConfig<*>.() -> Unit = {
        mockContentNegotiation()
        expectSuccess = true
    }

    @Test
    suspend fun `consumer propagates a cancellation raised while the error body is read`() {
        val client = f2Client(
            HttpStatusCode.InternalServerError,
            """{"message":"boom"}""",
            failingOnFirstErrorRead { CancellationException("caller went away") }
        )

        val failure = runCatching { client.testConsumer().invoke(flowOf(TestRequest("id", 1))) }.exceptionOrNull()

        assertThat(failure).isInstanceOf(CancellationException::class.java)
            .isNotInstanceOf(F2Exception::class.java)
    }

    @Test
    suspend fun `supplier propagates a cancellation raised while the error body is read`() {
        val client = f2Client(
            HttpStatusCode.InternalServerError,
            """{"message":"boom"}""",
            failingOnFirstErrorRead { CancellationException("caller went away") }
        )

        val failure = runCatching { client.testSupplier().invoke().toList() }.exceptionOrNull()

        assertThat(failure).isInstanceOf(CancellationException::class.java)
    }

    @Test
    suspend fun `error body cut while read keeps the HTTP status`() {
        val client = f2Client(
            HttpStatusCode.BadGateway,
            """{"message":"boom"}""",
            failingOnFirstErrorRead { IOException("connection reset") }
        )

        val exception = assertThrows<F2Exception> {
            client.testConsumer().invoke(flowOf(TestRequest("id", 1)))
        }

        assertThat(exception.error.code).isEqualTo(502)
        assertThat(exception.error.message).isEqualTo("connection reset")
    }

    @Test
    suspend fun `error body with malformed UTF-8 keeps the HTTP status`() {
        val client = f2Client(HttpStatusCode.ServiceUnavailable, byteArrayOf(0xC3.toByte(), 0x28, 0xFF.toByte()))

        val exception = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(503)
    }

    @Test
    suspend fun `error body in an unknown charset keeps the HTTP status`() {
        val client = f2Client(
            HttpStatusCode.ServiceUnavailable,
            "down".encodeToByteArray(),
            contentType = "application/json; charset=x-not-a-charset"
        )

        val exception = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(503)
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
    suspend fun `error body with a code that is not a number keeps it in the message`() {
        val client = f2Client(HttpStatusCode.Forbidden, """{"message":"Forbidden","code":"ACCESS_DENIED"}""")

        val exception = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(403)
        assertThat(exception.error.message).isEqualTo("Forbidden (code ACCESS_DENIED)")
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
    suspend fun `error body code is kept when the rest of the body cannot be decoded`() {
        val body = """{"code":409,"detail":"no message field"}"""
        val client = f2Client(HttpStatusCode.BadRequest, body)

        val exception = assertThrows<F2Exception> {
            client.testSupplier().invoke().toList()
        }

        assertThat(exception.error.code).isEqualTo(409)
        assertThat(exception.error.message).isEqualTo(body)
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
        val client = f2Client(HttpStatusCode.BadRequest, """{"message":"Invalid","code":400}""", expectingSuccess)

        val exception = assertThrows<F2Exception> {
            client.testConsumer().invoke(flowOf(TestRequest("id", 1)))
        }

        assertThat(exception.error.code).isEqualTo(400)
        assertThat(exception.error.message).isEqualTo("Invalid")
    }

    @Test
    suspend fun `consumer of a client expecting success sends the remaining messages after a failure`() {
        var requestCount = 0
        val client = mockF2Client(config = expectingSuccess) {
            requestCount++
            if (requestCount == 1) {
                respond(content = """{"message":"Invalid","code":400}""", status = HttpStatusCode.BadRequest)
            } else {
                respond(content = "", status = HttpStatusCode.OK)
            }
        }

        val exception = assertThrows<F2Exception> {
            client.testConsumer().invoke(flowOf(TestRequest("first", 1), TestRequest("second", 2)))
        }

        assertThat(exception.error.code).isEqualTo(400)
        assertThat(requestCount).isEqualTo(2)
    }

    @Test
    suspend fun `consumer propagates a response exception the caller raises on a successful response`() {
        val client = f2Client(HttpStatusCode.OK, "") {
            mockContentNegotiation()
            HttpResponseValidator {
                validateResponse { response -> throw ResponseException(response, "rejected by the caller") }
            }
        }

        val exception = assertThrows<ResponseException> {
            client.testConsumer().invoke(flowOf(TestRequest("id", 1)))
        }

        assertThat(exception.message).contains("rejected by the caller")
    }

    @Test
    suspend fun `function and supplier translate the response exception of a client expecting success`() {
        val client = f2Client(HttpStatusCode.ServiceUnavailable, "down", expectingSuccess)

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
