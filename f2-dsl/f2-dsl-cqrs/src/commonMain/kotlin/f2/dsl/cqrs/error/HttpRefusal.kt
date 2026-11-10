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
	FORBIDDEN(status = 403),
	NOT_FOUND(status = 404),
	METHOD_NOT_ALLOWED(status = 405),
	CONFLICT(status = 409),
	PAYLOAD_TOO_LARGE(status = 413),
	UNSUPPORTED_MEDIA_TYPE(status = 415),
	UNPROCESSABLE(status = 422),
	TOO_MANY_REQUESTS(status = 429),
	UNEXPECTED(status = 500),
	UNAVAILABLE(status = 503),
	;

	/** This meaning's code, `http.<NAME>`. */
	val refusal: String get() = "$DOMAIN.$name"

	companion object {
		/** The domain of the codes that name a status's meaning. */
		const val DOMAIN: String = "http"

		/** The meaning of [status]: a status no meaning here names is unexpected, a server error first among them. */
		fun of(status: Int): HttpRefusal = entries.firstOrNull { it.status == status } ?: UNEXPECTED
	}
}
