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
    fun `missingParameterError is a 400 refusal naming the parameter`() {
        val error = missingParameterError("userId")

        assertEquals(400, error.code)
        assertEquals("http.INVALID_PAYLOAD", error.refusal?.code)
        assertEquals(mapOf(PARAMETER_KEY to "userId"), error.refusal?.parameters)
        assertEquals("Missing parameter `userId`", error.message)
    }

    @Test
    fun `refusalCode is the refusal's, or the meaning of the status`() {
        val refused = F2Error(message = "x", code = 409, refusal = F2Refusal("order.CLOSED"))

        assertTrue(isRefusal(refused))
        assertEquals("order.CLOSED", refusalCode(refused))
        assertFalse(isRefusal(F2Error(message = "x", code = 404)))
        assertEquals("http.NOT_FOUND", refusalCode(F2Error(message = "x", code = 404)))
    }

    @Test
    fun `f2Error toString contains error fields`() {
        val error = F2Error(message = "boom", code = 400, timestamp = "now", requestId = "req-1")

        assertEquals(
            "F2Error(timestamp='now', code=400, requestId='req-1', refusal=null, message='boom')",
            error.toString(),
        )
    }
}
