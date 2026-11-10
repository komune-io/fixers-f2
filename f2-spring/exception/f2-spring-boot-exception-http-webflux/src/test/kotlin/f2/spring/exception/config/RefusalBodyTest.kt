package f2.spring.exception.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.http.server.reactive.MockServerHttpRequest
import org.springframework.mock.web.server.MockServerWebExchange
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.readValue

class RefusalBodyTest {

    private fun written(write: MockServerWebExchange.() -> Unit): Pair<MockServerWebExchange, Map<String, Any?>> {
        val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/items/i-1"))
        exchange.write()
        val text = exchange.response.bodyAsString.block().orEmpty()
        return exchange to JsonMapper.shared().readValue(text)
    }

    @Test
    fun `a status alone is answered with its meaning and no message`() {
        val (exchange, body) = written { writeRefusal(HttpStatus.UNAUTHORIZED).block() }

        assertThat(exchange.response.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
        assertThat(exchange.response.headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)
        assertThat(body.keys).containsExactlyInAnyOrder("timestamp", "path", "status", "error", "refusal", "requestId")
        assertThat(body).containsEntry("path", "/items/i-1")
            .containsEntry("status", 401)
            .containsEntry("error", "Unauthorized")
            .containsEntry("refusal", "http.UNAUTHENTICATED")
            .containsEntry("requestId", exchange.request.id)
    }

    @Test
    fun `a rule of the application's own is answered with its code and sentence`() {
        val (_, body) = written {
            writeRefusal(HttpStatus.FORBIDDEN, message = "This client may only read", refusal = "client.READ_ONLY")
                .block()
        }

        assertThat(body["status"]).isEqualTo(403)
        assertThat(body["refusal"]).isEqualTo("client.READ_ONLY")
        assertThat(body["message"]).isEqualTo("This client may only read")
    }
}
