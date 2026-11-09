package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.ServletWebRequest
import tools.jackson.core.JsonParser
import tools.jackson.core.exc.StreamReadException

class F2ErrorAttributesTest {

    private val errorAttributes = F2ErrorAttributes()
    private val internal = "jdbc:postgresql://db-internal:5432/app"

    private fun bodyFor(
        exception: Throwable?,
        options: ErrorAttributeOptions = ErrorAttributeOptions.defaults(),
    ): Map<String, Any?> {
        val request = MockHttpServletRequest()
        request.setAttribute("jakarta.servlet.error.status_code", 500)
        request.setAttribute("jakarta.servlet.error.request_uri", "/test")
        if (exception != null) {
            request.setAttribute("jakarta.servlet.error.exception", exception)
        }
        return errorAttributes.getErrorAttributes(ServletWebRequest(request), options)
    }

    @Test
    fun `a refusal tells the caller its rule, its sentence and the values it names`() {
        val refusal = F2Exception(
            error = F2Error(
                message = "Cited by A: it stays", code = 409, refusal = "item.CITED",
                parameters = mapOf("id" to "i-1"), citers = listOf("A"),
            )
        )

        val body = bodyFor(IllegalStateException("wrapper", refusal))

        assertThat(body["status"]).isEqualTo(409)
        assertThat(body["refusal"]).isEqualTo("item.CITED")
        assertThat(body["message"]).isEqualTo("Cited by A: it stays")
        assertThat(body["parameters"]).isEqualTo(mapOf("id" to "i-1"))
        assertThat(body["citers"]).isEqualTo(listOf("A"))
        assertThat(body).doesNotContainKeys("id", "code")
    }

    @Test
    fun `an f2 exception that is not a refusal answers its status and nothing it was raised with`() {
        val body = bodyFor(F2Exception(message = internal, id = "error-id", code = 404))

        assertThat(body["status"]).isEqualTo(404)
        assertThat(body["refusal"]).isEqualTo("http.NOT_FOUND")
        assertThat(body).doesNotContainKeys("message", "id", "code", "parameters", "citers")
    }

    @Test
    fun `any other exception says nothing it was raised with, even when configuration asks for messages`() {
        val body = bodyFor(
            IllegalStateException(internal),
            ErrorAttributeOptions.defaults().including(ErrorAttributeOptions.Include.MESSAGE),
        )

        assertThat(body["status"]).isEqualTo(500)
        assertThat(body["refusal"]).isEqualTo("http.UNEXPECTED")
        assertThat(body).doesNotContainKey("message")
    }

    @Test
    fun `a body the parser cannot read is the caller's`() {
        val parser = StreamReadException(null as JsonParser?, "Unexpected end-of-input")

        val body = bodyFor(IllegalStateException("Failed to convert input", RuntimeException(parser)))

        assertThat(body["status"]).isEqualTo(400)
        assertThat(body["refusal"]).isEqualTo("http.INVALID_PAYLOAD")
        assertThat(body).doesNotContainKey("message")
    }

    @Test
    fun `an error without an exception carries the meaning of its status`() {
        val body = bodyFor(null)

        assertThat(body["refusal"]).isEqualTo("http.UNEXPECTED")
    }
}
