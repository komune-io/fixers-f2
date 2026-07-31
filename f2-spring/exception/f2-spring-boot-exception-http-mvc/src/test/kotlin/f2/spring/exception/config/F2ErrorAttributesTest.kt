package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.ServletWebRequest

class F2ErrorAttributesTest {

    private val errorAttributes = F2ErrorAttributes()

    private fun webRequest(exception: Throwable?): ServletWebRequest {
        val request = MockHttpServletRequest()
        request.setAttribute("jakarta.servlet.error.status_code", 500)
        request.setAttribute("jakarta.servlet.error.request_uri", "/test")
        if (exception != null) {
            request.setAttribute("jakarta.servlet.error.exception", exception)
        }
        return ServletWebRequest(request)
    }

    @Test
    fun `should enrich attributes with f2 error fields for F2Exception`() {
        val exception = F2Exception(message = "Something broke", id = "error-id", code = 404)

        val attributes = errorAttributes.getErrorAttributes(
            webRequest(exception),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["id"]).isEqualTo("error-id")
        assertThat(attributes["code"]).isEqualTo(404)
        assertThat(attributes["message"]).isEqualTo("Something broke")
        assertThat(attributes["timestamp"]).isNotNull
    }

    @Test
    fun `should keep default attributes for non f2 exceptions`() {
        val attributes = errorAttributes.getErrorAttributes(
            webRequest(IllegalStateException("unexpected")),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["code"]).isNull()
        assertThat(attributes["id"]).isNull()
        assertThat(attributes["message"]).isEqualTo("unexpected")
        assertThat(attributes["status"]).isEqualTo(500)
    }

    @Test
    fun `should keep default attributes without exception`() {
        val attributes = errorAttributes.getErrorAttributes(
            webRequest(null),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["code"]).isNull()
        assertThat(attributes["status"]).isEqualTo(500)
    }
}
