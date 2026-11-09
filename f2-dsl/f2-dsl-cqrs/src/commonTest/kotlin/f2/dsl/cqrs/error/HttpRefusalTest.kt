package f2.dsl.cqrs.error

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class HttpRefusalTest {

    @Test
    fun `each status a meaning names maps to that meaning`() {
        val meanings = mapOf(
            400 to "http.INVALID_PAYLOAD",
            401 to "http.UNAUTHENTICATED",
            403 to "http.FORBIDDEN",
            404 to "http.NOT_FOUND",
            405 to "http.METHOD_NOT_ALLOWED",
            409 to "http.CONFLICT",
            413 to "http.PAYLOAD_TOO_LARGE",
            415 to "http.UNSUPPORTED_MEDIA_TYPE",
            422 to "http.UNPROCESSABLE",
            429 to "http.TOO_MANY_REQUESTS",
            500 to "http.UNEXPECTED",
            503 to "http.UNAVAILABLE",
        )

        meanings.forEach { (status, code) -> assertEquals(code, HttpRefusal.of(status).refusal, "status $status") }
        assertEquals(HttpRefusal.entries.size, meanings.size)
    }

    @Test
    fun `a status no meaning names is unexpected`() {
        listOf(406, 418, 502, 504, 999, 0).forEach { status ->
            assertEquals(HttpRefusal.UNEXPECTED, HttpRefusal.of(status), "status $status")
        }
    }

    @Test
    fun `an error is not a refusal until it names one`() {
        val error = F2Error(message = "boom")

        assertNull(error.refusal)
        assertTrue(error.parameters.isEmpty())
        assertTrue(error.citers.isEmpty())
    }
}
