package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.http.HttpStatus
import org.springframework.mock.http.server.reactive.MockServerHttpRequest
import org.springframework.mock.web.server.MockServerWebExchange
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException

class F2ErrorAttributesTest {

    private val errorAttributes = F2ErrorAttributes()

    private fun serverRequest(exception: Throwable): ServerRequest {
        val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/test"))
        errorAttributes.storeErrorInformation(exception, exchange)
        return ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders())
    }

    @Test
    fun `should enrich attributes with f2 error fields for F2Exception`() {
        val exception = F2Exception(message = "Something broke", id = "error-id", code = 404)

        val attributes = errorAttributes.getErrorAttributes(
            serverRequest(exception),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["id"]).isEqualTo("error-id")
        assertThat(attributes["code"]).isEqualTo(404)
        assertThat(attributes["message"]).isEqualTo("Something broke")
        assertThat(attributes["timestamp"]).isNotNull
    }

    @Test
    fun `should enrich attributes when F2Exception is the cause`() {
        val cause = F2Exception(message = "Nested failure", id = "cause-id", code = 409)
        val exception = IllegalStateException("wrapper", cause)

        val attributes = errorAttributes.getErrorAttributes(
            serverRequest(exception),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["id"]).isEqualTo("cause-id")
        assertThat(attributes["code"]).isEqualTo(409)
        assertThat(attributes["message"]).isEqualTo("Nested failure")
    }

    @Test
    fun `should keep default attributes for non f2 exceptions`() {
        val attributes = errorAttributes.getErrorAttributes(
            serverRequest(IllegalStateException("unexpected")),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["code"]).isNull()
        assertThat(attributes["id"]).isNull()
        assertThat(attributes["status"]).isEqualTo(500)
    }

    @Test
    fun `should resolve status from a raw ResponseStatusException that is not an F2Exception`() {
        val exception = ResponseStatusException(HttpStatus.BAD_REQUEST, "raw bad request")

        val attributes = errorAttributes.getErrorAttributes(
            serverRequest(exception),
            ErrorAttributeOptions.defaults()
        )

        assertThat(attributes["code"]).isNull()
        assertThat(attributes["status"]).isEqualTo(400)
    }
}
