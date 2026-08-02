package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class F2ErrorExtensionTest {

    @Test
    fun `asException wraps error without cause`() {
        val error = F2Error(message = "boom", code = 418)

        val exception = error.asException()

        assertThat(exception.error).isEqualTo(error)
        assertThat(exception.cause).isNull()
        assertThat(exception.message).isEqualTo("boom")
    }

    @Test
    fun `asException wraps error with cause`() {
        val error = F2Error(message = "boom")
        val cause = IllegalStateException("cause")

        val exception = error.asException(cause)

        assertThat(exception.error).isEqualTo(error)
        assertThat(exception.cause).isEqualTo(cause)
    }

    @Test
    fun `throwException throws an F2Exception carrying the error`() {
        val error = F2Error(message = "boom")

        assertThatThrownBy { error.throwException() }
            .isInstanceOf(F2Exception::class.java)
            .hasMessage("boom")

        val cause = IllegalArgumentException("cause")
        assertThatThrownBy { error.throwException(cause) }
            .isInstanceOf(F2Exception::class.java)
            .hasCause(cause)
    }

    @Test
    fun `asError builds an F2Error from an exception`() {
        assertThat(IllegalStateException("oops").asError().message).isEqualTo("oops")
        assertThat(IllegalStateException().asError().message).isEqualTo("Unknown error")
    }

    @Test
    fun `f2Error toString contains error fields`() {
        val error = F2Error(message = "boom", code = 400, timestamp = "now", requestId = "req-1")

        assertThat(error.toString())
            .isEqualTo("F2Error(timestamp='now', code=400, requestId='req-1', message='boom')")
    }
}
