package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.readValue

class F2ExceptionResolverTest {

    private val resolver = F2ExceptionResolver(JsonMapper.shared())

    private fun resolve(ex: Exception): Pair<MockHttpServletResponse, Boolean> {
        val response = MockHttpServletResponse()
        val handled = resolver.resolveException(MockHttpServletRequest("POST", "/itemUpdate"), response, null, ex)
        return response to (handled != null)
    }

    private fun MockHttpServletResponse.body(): Map<String, Any?> = JsonMapper.shared().readValue(contentAsString)

    @Test
    fun `a refusal wrapped once answers its own status, in the response and the body`() {
        val refusal = F2Exception(error = F2Error(message = "Cited", code = 409, refusal = F2Refusal("item.CITED")))

        val (response, handled) = resolve(IllegalStateException("wrapper", refusal))

        assertThat(handled).isTrue()
        assertThat(response.status).isEqualTo(409)
        assertThat(response.body()).containsEntry("status", 409).containsEntry("refusal", "item.CITED")
            .containsEntry("path", "/itemUpdate")
    }

    @Test
    fun `a refusal wrapped twice still answers its own status`() {
        val missing = F2Exception(error = missingParameterError("description"))

        val wrapped = IllegalStateException("Failed to convert input", IllegalStateException("c", missing))

        val (response, _) = resolve(wrapped)

        assertThat(response.status).isEqualTo(400)
        assertThat(response.body()["message"]).isEqualTo("Missing parameter `description`")
    }

    @Test
    fun `an input that converts to nothing is the caller's`() {
        val (response, handled) = resolve(IllegalArgumentException("Failed to convert input: {} to Item"))

        assertThat(handled).isTrue()
        assertThat(response.status).isEqualTo(400)
        assertThat(response.body()).containsEntry("refusal", "http.INVALID_PAYLOAD")
            .containsEntry("message", "Unreadable request body")
    }

    @Test
    fun `an F2 exception answers its own status, direct or wrapped at any depth`() {
        listOf(
            F2Exception(F2Error(message = "x", code = 409)),
            RuntimeException("wrapper", RuntimeException("again", F2Exception(F2Error(message = "x", code = 409)))),
        ).forEach { ex ->
            val (response, handled) = resolve(ex)
            assertThat(handled).`as`(ex.toString()).isTrue()
            assertThat(response.status).isEqualTo(409)
            assertThat(response.body()).containsEntry("refusal", "http.CONFLICT").doesNotContainKey("message")
        }
    }

    @Test
    fun `any failure carrying nothing of F2 is left to the application and to Spring`() {
        listOf(
            IllegalStateException("connect to db-internal failed"),
            IllegalArgumentException("bad id"),
        ).forEach { ex ->
            val (response, handled) = resolve(ex)
            assertThat(handled).`as`(ex.toString()).isFalse()
            assertThat(response.contentAsString).isEmpty()
        }
    }
}
