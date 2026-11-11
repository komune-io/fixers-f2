package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.exception.F2Exception
import jakarta.servlet.FilterChain
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.readValue

class F2ExceptionFilterTest {

    private val filter = F2ExceptionFilter(JsonMapper.shared())
    private val refusal = F2Exception(
        F2Error(message = "Read only", code = 403, refusal = F2Refusal("client.READ_ONLY")),
    )
    private val refusing = FilterChain { _, _ -> throw refusal }

    @Test
    fun `a refusal a servlet filter raises answers its own status and body, not the error page's 500`() {
        val response = MockHttpServletResponse()

        filter.doFilter(MockHttpServletRequest("POST", "/itemUpdate"), response, refusing)

        val body: Map<String, Any?> = JsonMapper.shared().readValue(response.contentAsString)
        assertThat(response.status).isEqualTo(403)
        assertThat(body).containsEntry("refusal", "client.READ_ONLY")
            .containsEntry("message", "Read only")
            .containsEntry("path", "/itemUpdate")
    }

    @Test
    fun `a response already committed lets the refusal through`() {
        val response = MockHttpServletResponse().apply { setCommitted(true) }

        assertThatThrownBy { filter.doFilter(MockHttpServletRequest("POST", "/itemUpdate"), response, refusing) }
            .isSameAs(refusal)
    }
}
