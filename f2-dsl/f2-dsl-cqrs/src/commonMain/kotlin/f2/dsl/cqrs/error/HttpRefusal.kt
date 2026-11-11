package f2.dsl.cqrs.error

import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * The meaning of an HTTP status, as the refusal code an error carries when no rule of the application names one:
 * every error a caller receives carries a code, and that code discloses nothing its status did not.
 *
 * Never reused for another meaning: a caller words the meaning from the code.
 */
@JsExport
@JsName("HttpRefusal")
enum class HttpRefusal(val status: Int) {
    INVALID_PAYLOAD(status = 400),
    UNAUTHENTICATED(status = 401),
    PAYMENT_REQUIRED(status = 402),
    FORBIDDEN(status = 403),
    NOT_FOUND(status = 404),
    METHOD_NOT_ALLOWED(status = 405),
    NOT_ACCEPTABLE(status = 406),
    PROXY_UNAUTHENTICATED(status = 407),
    REQUEST_TIMEOUT(status = 408),
    CONFLICT(status = 409),
    GONE(status = 410),
    LENGTH_REQUIRED(status = 411),
    PRECONDITION_FAILED(status = 412),
    PAYLOAD_TOO_LARGE(status = 413),
    URI_TOO_LONG(status = 414),
    UNSUPPORTED_MEDIA_TYPE(status = 415),
    RANGE_NOT_SATISFIABLE(status = 416),
    EXPECTATION_FAILED(status = 417),
    MISDIRECTED_REQUEST(status = 421),
    UNPROCESSABLE(status = 422),
    LOCKED(status = 423),
    FAILED_DEPENDENCY(status = 424),
    TOO_EARLY(status = 425),
    UPGRADE_REQUIRED(status = 426),
    PRECONDITION_REQUIRED(status = 428),
    TOO_MANY_REQUESTS(status = 429),
    HEADERS_TOO_LARGE(status = 431),
    UNAVAILABLE_FOR_LEGAL_REASONS(status = 451),
    UNEXPECTED(status = 500),
    UNAVAILABLE(status = 503),
    ;

    /** This meaning's code, `http.<NAME>`. */
    val refusal: String get() = "$DOMAIN.$name"

    companion object {
        /** The domain of the codes that name a status's meaning. */
        const val DOMAIN: String = "http"

        /**
         * The meaning of [status]: every client error RFC 9110 and its companions (6585, 7725, 8470, 4918) define
         * has its own; any other status — a server error first among them, a non-standard one too — is unexpected.
         */
        fun ofStatus(status: Int): HttpRefusal = entries.firstOrNull { it.status == status } ?: UNEXPECTED
    }
}
