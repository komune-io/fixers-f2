package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import tools.jackson.module.kotlin.KotlinInvalidNullException
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue

class F2ExceptionHandlerTest {

    private val handler = F2ExceptionHandler()
    private val request = MockHttpServletRequest("POST", "/itemUpdate")

    data class Sample(val name: String)

    @Test
    fun `handleF2Exception should map error code to http status, without the message of a non-refusal`() {
        val exception = F2Exception(message = "User not found", id = "error-id", code = 404)

        val response = handler.handleF2Exception(exception, request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.body!!.keys).containsExactly("timestamp", "path", "status", "error", "refusal")
        assertThat(response.body).containsEntry("path", "/itemUpdate").containsEntry("status", 404)
            .containsEntry("refusal", "http.NOT_FOUND")
    }

    @Test
    fun `handleF2Exception should tell a refusal's code, message, parameters and citers`() {
        val exception = F2Exception(
            error = F2Error(
                message = "Cited by A: it stays", code = 409, refusal = "item.CITED",
                parameters = mapOf("id" to "i-1"), citers = listOf("A"),
            )
        )

        val response = handler.handleF2Exception(exception, request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.CONFLICT)
        assertThat(response.body!!["refusal"]).isEqualTo("item.CITED")
        assertThat(response.body!!["message"]).isEqualTo("Cited by A: it stays")
        assertThat(response.body!!["parameters"]).isEqualTo(mapOf("id" to "i-1"))
        assertThat(response.body!!["citers"]).isEqualTo(listOf("A"))
    }

    @Test
    fun `handleF2Exception should default to internal server error for unknown code`() {
        val response = handler.handleF2Exception(F2Exception(message = "Broken", code = 999), request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @Test
    fun `a refusal wrapped once by the function runtime answers its own status, in the response and the body`() {
        val refusal = F2Exception(error = F2Error(message = "Cited", code = 409, refusal = "item.CITED"))

        val response = handler.handleWrapped(IllegalStateException("wrapper", refusal), request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.CONFLICT)
        assertThat(response.body!!["status"]).isEqualTo(409)
    }

    @Test
    fun `a refusal wrapped twice by the function runtime still answers its own status`() {
        val missing = F2Exception(error = f2.dsl.cqrs.error.missingParameterError("description"))
        val wrapped = IllegalStateException("Failed to convert input", IllegalStateException("convert", missing))

        val response = handler.handleWrapped(wrapped, request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body!!["message"]).isEqualTo("Missing parameter `description`")
    }

    @Test
    fun `a missing parameter wrapped by the function runtime is the caller's`() {
        val response = handler.handleWrapped(IllegalStateException("convert", kotlinInvalidNullException()), request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body!!["parameters"]).isEqualTo(mapOf("parameter" to "name"))
    }

    @Test
    fun `an input that converts to nothing is the caller's`() {
        val response = handler.handleWrapped(IllegalArgumentException("Failed to convert input: {} to Item"), request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).containsEntry("refusal", "http.INVALID_PAYLOAD").doesNotContainKey("message")
    }

    @Test
    fun `any other wrapper is thrown back, to Spring's own resolution`() {
        val internal = IllegalStateException("connect to db-internal failed")

        assertThatThrownBy { handler.handleWrapped(internal, request) }.isSameAs(internal)
    }

    @Test
    fun `handleKotlinNullException should return bad request with missing parameter message`() {
        val response = handler.handleKotlinNullException(kotlinInvalidNullException(), request)

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body!!["status"]).isEqualTo(400)
        assertThat(response.body!!["refusal"]).isEqualTo("http.INVALID_PAYLOAD")
        assertThat(response.body!!["message"]).isEqualTo("Missing parameter `name`")
        assertThat(response.body!!["parameters"]).isEqualTo(mapOf("parameter" to "name"))
    }

    private fun kotlinInvalidNullException(): KotlinInvalidNullException {
        var exception: KotlinInvalidNullException? = null
        assertThatThrownBy {
            jacksonObjectMapper().readValue<Sample>("""{"name":null}""")
        }.isInstanceOf(KotlinInvalidNullException::class.java)
            .satisfies({ thrown -> exception = thrown as KotlinInvalidNullException })
        return exception!!
    }
}
