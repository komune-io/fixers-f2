package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import kotlin.test.assertNull

class F2ErrorExtensionTest {

    @Test
    fun `asException wraps error without cause`() {
        val error = F2Error(message = "boom", code = 418)

        val exception = error.asException()

        assertEquals(error, exception.error)
        assertNull(exception.cause)
        assertEquals("boom", exception.message)
    }

    @Test
    fun `asException wraps error with cause`() {
        val error = F2Error(message = "boom")
        val cause = IllegalStateException("cause")

        val exception = error.asException(cause)

        assertEquals(error, exception.error)
        assertEquals(cause, exception.cause)
    }

    @Test
    fun `throwException throws an F2Exception carrying the error`() {
        val error = F2Error(message = "boom")

        val exception = assertFailsWith<F2Exception> { error.throwException() }
        assertEquals("boom", exception.message)

        val cause = IllegalArgumentException("cause")
        val exceptionWithCause = assertFailsWith<F2Exception> { error.throwException(cause) }
        assertEquals(cause, exceptionWithCause.cause)
    }

    @Test
    fun `asError builds an F2Error from an exception`() {
        assertEquals("oops", IllegalStateException("oops").asError().message)
        assertEquals("Unknown error", IllegalStateException().asError().message)
    }

    @Test
    fun `f2Error toString contains error fields`() {
        val error = F2Error(message = "boom", code = 400, timestamp = "now", requestId = "req-1")

        assertEquals("F2Error(timestamp='now', code=400, requestId='req-1', message='boom')", error.toString())
    }
}
