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
            402 to "http.PAYMENT_REQUIRED",
            403 to "http.FORBIDDEN",
            404 to "http.NOT_FOUND",
            405 to "http.METHOD_NOT_ALLOWED",
            406 to "http.NOT_ACCEPTABLE",
            407 to "http.PROXY_UNAUTHENTICATED",
            408 to "http.REQUEST_TIMEOUT",
            409 to "http.CONFLICT",
            410 to "http.GONE",
            411 to "http.LENGTH_REQUIRED",
            412 to "http.PRECONDITION_FAILED",
            413 to "http.PAYLOAD_TOO_LARGE",
            414 to "http.URI_TOO_LONG",
            415 to "http.UNSUPPORTED_MEDIA_TYPE",
            416 to "http.RANGE_NOT_SATISFIABLE",
            417 to "http.EXPECTATION_FAILED",
            421 to "http.MISDIRECTED_REQUEST",
            422 to "http.UNPROCESSABLE",
            423 to "http.LOCKED",
            424 to "http.FAILED_DEPENDENCY",
            425 to "http.TOO_EARLY",
            426 to "http.UPGRADE_REQUIRED",
            428 to "http.PRECONDITION_REQUIRED",
            429 to "http.TOO_MANY_REQUESTS",
            431 to "http.HEADERS_TOO_LARGE",
            451 to "http.UNAVAILABLE_FOR_LEGAL_REASONS",
            500 to "http.UNEXPECTED",
            503 to "http.UNAVAILABLE",
        )

        meanings.forEach { (status, code) -> assertEquals(code, HttpRefusal.of(status).refusal, "status $status") }
        assertEquals(HttpRefusal.entries.size, meanings.size)
    }

    @Test
    fun `every standard client error has a meaning of its own`() {
        (400..499).filter { it in STANDARD_CLIENT_ERRORS }.forEach { status ->
            assertTrue(HttpRefusal.of(status) != HttpRefusal.UNEXPECTED, "status $status")
        }
    }

    @Test
    fun `a status no meaning names is unexpected`() {
        listOf(418, 420, 499, 502, 504, 999, 0).forEach { status ->
            assertEquals(HttpRefusal.UNEXPECTED, HttpRefusal.of(status), "status $status")
        }
    }

    @Test
    fun `an error is not a refusal until it names one`() {
        val error = F2Error(message = "boom")

        assertNull(error.refusal)
        assertNull(error.id)
    }

    private companion object {
        val STANDARD_CLIENT_ERRORS = setOf(
            400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 421, 422,
            423, 424, 425, 426, 428, 429, 431, 451,
        )
    }
}
