package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.ConflictException
import f2.spring.exception.F2HttpException
import f2.spring.exception.ForbiddenAccessException
import f2.spring.exception.NotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.http.HttpStatus
import org.springframework.mock.http.server.reactive.MockServerHttpRequest
import org.springframework.mock.web.server.MockServerWebExchange
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebInputException
import tools.jackson.core.JsonParser
import tools.jackson.core.exc.StreamReadException

/**
 * What a caller is told about an exception, and what it is not: the map the reactive error handler serializes, so
 * a key missing here is a key missing from the response.
 */
class F2ErrorAttributesTest {

    private val internal = "jdbc:postgresql://db-internal:5432/app"

    private fun bodyFor(
        error: Throwable,
        options: ErrorAttributeOptions = ErrorAttributeOptions.defaults(),
    ): Map<String, Any?> {
        val errorAttributes = F2ErrorAttributes()
        val exchange = MockServerWebExchange.from(MockServerHttpRequest.post("/itemUpdate"))
        errorAttributes.storeErrorInformation(error, exchange)
        val request = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders())
        return errorAttributes.getErrorAttributes(request, options)
    }

    @Test
    fun `a refusal tells the caller its rule, its sentence and the values it names`() {
        val body = bodyFor(
            F2HttpException(
                status = HttpStatus.BAD_REQUEST, message = "The link name \"me\" is reserved", cause = null,
                refusal = "item.ALIAS_RESERVED", parameters = mapOf("alias" to "me"),
            )
        )

        assertThat(body["status"]).isEqualTo(400)
        assertThat(body["refusal"]).isEqualTo("item.ALIAS_RESERVED")
        assertThat(body["message"]).isEqualTo("The link name \"me\" is reserved")
        assertThat(body["parameters"]).isEqualTo(mapOf("alias" to "me"))
        assertThat(body).doesNotContainKeys("citers", "id", "code")
    }

    @Test
    fun `an object kept because others cite it names them beside its rule`() {
        val body = bodyFor(
            F2HttpException(
                status = HttpStatus.CONFLICT, message = "Cited by A, B: it stays", cause = null,
                refusal = "item.CITED", citers = listOf("A", "B"),
            )
        )

        assertThat(body["status"]).isEqualTo(409)
        assertThat(body["citers"]).isEqualTo(listOf("A", "B"))
        assertThat(body).doesNotContainKey("parameters")
    }

    @Test
    fun `a refusal is read through the wrapper Spring puts around a handler failure`() {
        val refusal = F2Exception(
            error = f2.dsl.cqrs.error.F2Error(message = "Too wide", code = 422, refusal = "read.TOO_WIDE")
        )

        val body = bodyFor(IllegalStateException("wrapper", refusal))

        assertThat(body["status"]).isEqualTo(422)
        assertThat(body["error"]).isEqualTo("Unprocessable Content")
        assertThat(body["refusal"]).isEqualTo("read.TOO_WIDE")
        assertThat(body["message"]).isEqualTo("Too wide")
    }

    @Test
    fun `an error no rule names carries the code of its status, and nothing it was raised with`() {
        val bodies = mapOf(
            ResponseStatusException(HttpStatus.UNAUTHORIZED, internal) to "http.UNAUTHENTICATED",
            ForbiddenAccessException(internal) to "http.FORBIDDEN",
            NotFoundException("Item", internal) to "http.NOT_FOUND",
            ConflictException("Item", "name", internal) to "http.CONFLICT",
            ResponseStatusException(HttpStatus.CONTENT_TOO_LARGE, internal) to "http.PAYLOAD_TOO_LARGE",
            ServerWebInputException(internal) to "http.INVALID_PAYLOAD",
            F2Exception(message = internal, code = 400) to "http.INVALID_PAYLOAD",
            ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, internal) to "http.METHOD_NOT_ALLOWED",
            ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, internal) to "http.UNSUPPORTED_MEDIA_TYPE",
            ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, internal) to "http.UNPROCESSABLE",
            ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, internal) to "http.TOO_MANY_REQUESTS",
            ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, internal) to "http.UNAVAILABLE",
            ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, internal) to "http.UNEXPECTED",
            IllegalArgumentException(internal) to "http.UNEXPECTED",
        ).mapKeys { (error, _) -> bodyFor(error) }

        bodies.forEach { (body, code) ->
            assertThat(body["refusal"]).isEqualTo(code)
            assertThat(body).doesNotContainKeys("message", "parameters", "citers", "id", "code")
            assertThat(body.values.map(Any?::toString)).noneMatch { it.contains("db-internal") }
        }
    }

    @Test
    fun `an f2 exception answers with the status its error names`() {
        val body = bodyFor(F2Exception(message = internal, code = 404))

        assertThat(body["status"]).isEqualTo(404)
        assertThat(body["error"]).isEqualTo("Not Found")
    }

    @Test
    fun `a message asked for by configuration is still withheld from anything but a refusal`() {
        val body = bodyFor(
            IllegalStateException(internal),
            ErrorAttributeOptions.defaults().including(ErrorAttributeOptions.Include.MESSAGE),
        )

        assertThat(body).doesNotContainKey("message")
        assertThat(body["refusal"]).isEqualTo("http.UNEXPECTED")
    }

    @Test
    fun `a body the parser cannot read is the caller's, and says nothing it was parsed with`() {
        val parser = StreamReadException(null as JsonParser?, "Unexpected end-of-input near $internal")

        val body = bodyFor(IllegalStateException("Failed to convert input", RuntimeException(parser)))

        assertThat(body["status"]).isEqualTo(400)
        assertThat(body["error"]).isEqualTo("Bad Request")
        assertThat(body["refusal"]).isEqualTo("http.INVALID_PAYLOAD")
        assertThat(body).doesNotContainKey("message")
    }

    @Test
    fun `a parser failure the server met on its own reading stays a server error`() {
        val parser = StreamReadException(null as JsonParser?, "Unexpected end-of-input")

        val body = bodyFor(IllegalStateException("reading the cache", parser))

        assertThat(body["status"]).isEqualTo(500)
        assertThat(body["refusal"]).isEqualTo("http.UNEXPECTED")
    }
}
