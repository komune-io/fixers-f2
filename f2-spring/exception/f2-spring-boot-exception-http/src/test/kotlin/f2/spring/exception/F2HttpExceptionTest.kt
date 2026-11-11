package f2.spring.exception

import f2.dsl.cqrs.error.F2Refusal
import java.time.Instant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class F2HttpExceptionTest {

    @Test
    fun `should set status code from HttpStatus`() {
        val exception = F2HttpException(
            status = HttpStatus.BAD_REQUEST,
            message = "bad request",
            cause = null
        )
        assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(exception.error.code).isEqualTo(400)
    }

    @Test
    fun `should propagate message to error and exception`() {
        val exception = F2HttpException(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            message = "something went wrong",
            cause = null
        )
        assertThat(exception.error.message).isEqualTo("something went wrong")
        assertThat(exception.message).isEqualTo("something went wrong")
    }

    @Test
    fun `should propagate cause`() {
        val rootCause = RuntimeException("root")
        val exception = F2HttpException(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            message = "wrapper",
            cause = rootCause
        )
        assertThat(exception.cause).isSameAs(rootCause)
    }

    @Test
    fun `should generate non-null id and timestamp`() {
        val exception = F2HttpException(
            status = HttpStatus.NOT_FOUND,
            message = "not found",
            cause = null
        )
        assertThat(exception.error.id).isNotBlank()
        assertThat(exception.error.timestamp).isNotBlank()
        assertThat(Instant.parse(exception.error.timestamp)).isNotNull()
    }

    @Test
    fun `a refusal carries its code, parameters and citers in its error`() {
        val exception = F2HttpException(
            status = HttpStatus.CONFLICT,
            message = "The item is cited: it stays",
            refusal = F2Refusal("item.CITED", parameters = mapOf("id" to "i-1"), citers = listOf("A")),
        )
        assertThat(exception.error.refusal?.code).isEqualTo("item.CITED")
        assertThat(exception.error.refusal?.parameters).containsExactlyEntriesOf(mapOf("id" to "i-1"))
        assertThat(exception.error.refusal?.citers).containsExactly("A")
    }

    @Test
    fun `an exception without a refusal code is not a refusal`() {
        val exception = F2HttpException(status = HttpStatus.NOT_FOUND, message = "not found", cause = null)
        assertThat(exception.error.refusal).isNull()
    }
}
