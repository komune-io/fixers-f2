package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class F2ErrorExtensionTest {

    @Test
    fun `asException wraps error without cause`() {
        val error = F2Error(message = "boom", code = 418)

        val exception = error.asException()

        assertEquals(expected = error, actual = exception.error)
        assertNull(exception.cause)
        assertEquals(expected = "boom", actual = exception.message)
    }

    @Test
    fun `asException wraps error with cause`() {
        val error = F2Error(message = "boom")
        val cause = IllegalStateException("cause")

        val exception = error.asException(cause)

        assertEquals(expected = error, actual = exception.error)
        assertEquals(expected = cause, actual = exception.cause)
    }

    @Test
    fun `throwException throws an F2Exception carrying the error`() {
        val error = F2Error(message = "boom")

        val exception = assertFailsWith<F2Exception> { error.throwException() }
        assertEquals(expected = "boom", actual = exception.message)

        val cause = IllegalArgumentException("cause")
        val exceptionWithCause = assertFailsWith<F2Exception> { error.throwException(cause) }
        assertEquals(expected = cause, actual = exceptionWithCause.cause)
    }

    @Test
    fun `missingParameterError is a 400 refusal naming the parameter`() {
        val error = missingParameterError("userId")

        assertEquals(expected = 400, actual = error.code)
        assertEquals(expected = "http.INVALID_PAYLOAD", actual = error.refusal?.code)
        assertEquals(expected = mapOf(PARAMETER_KEY to "userId"), actual = error.refusal?.parameters)
        assertEquals(expected = "Missing parameter `userId`", actual = error.message)
    }

    @Test
    fun `refusalCode is the refusal's, or the meaning of the status`() {
        val refused = F2Error(message = "x", code = 409, refusal = F2Refusal("order.CLOSED"))

        assertTrue(isRefusal(refused))
        assertEquals(expected = "order.CLOSED", actual = refusalCode(refused))
        assertFalse(isRefusal(F2Error(message = "x", code = 404)))
        assertEquals(expected = "http.NOT_FOUND", actual = refusalCode(F2Error(message = "x", code = 404)))
    }

    @Test
    fun `f2Error toString contains error fields`() {
        val error = F2Error(message = "boom", code = 400, timestamp = "now", requestId = "req-1")

        assertEquals(
            expected = "F2Error(timestamp='now', code=400, requestId='req-1', refusal=null, message='boom')",
            actual = error.toString(),
        )
    }
}
