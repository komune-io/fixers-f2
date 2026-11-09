package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
import f2.dsl.cqrs.error.HttpRefusal
import f2.dsl.cqrs.exception.F2Exception
import java.util.UUID
import tools.jackson.core.JacksonException

/** The error body's key for the refusal code. */
const val REFUSAL_ATTRIBUTE: String = "refusal"

/** `SimpleFunctionRegistry.FunctionInvocationWrapper`'s message when converting a function's input throws. */
private const val UNREADABLE_INPUT = "Failed to convert input"

/**
 * What an error body tells a caller beside the status: the refusal code always — the rule [error] names, or the
 * meaning of [status] — and, for a refusal only, its message, parameters and citers. Any other error says nothing
 * it was raised with: its message was not written for the caller, and may carry ids, hosts or SQL.
 */
fun refusalAttributes(status: Int, error: F2ErrorDTO?): Map<String, Any> = buildMap {
	val refusal = error?.refusal
	put(REFUSAL_ATTRIBUTE, refusal ?: HttpRefusal.of(status).refusal)
	if (refusal != null) {
		put(F2Error::message.name, error.message)
		error.parameters.takeIf { it.isNotEmpty() }?.let { put(F2Error::parameters.name, it) }
		error.citers.takeIf { it.isNotEmpty() }?.let { put(F2Error::citers.name, it) }
	}
}

/**
 * Finds the [F2Exception] carried by this throwable, either directly or as its immediate cause.
 *
 * Handlers that run behind a framework wrapper (Spring wraps handler failures before they reach the
 * error attributes) only ever see one level of wrapping, so this deliberately does not walk the
 * whole cause chain: a deeper [F2Exception] is an unrelated internal failure, not the error the
 * endpoint meant to report.
 */
fun Throwable?.findF2Exception(): F2Exception? = this as? F2Exception ?: this?.cause as? F2Exception

/**
 * The parser's failure on a request body it could not read at all, or `null`. A value of the wrong type is a
 * binding error F2 answers 400 itself, but a text cut short is a parser error, which Spring Cloud Function hands
 * over wrapped in an `IllegalStateException`. Only that wrapper counts: a parser failing on something the server
 * read itself is a server error.
 */
fun Throwable.unreadableBody(): JacksonException? =
	takeIf { it is IllegalStateException && it.message == UNREADABLE_INPUT }
		?.let { generateSequence(cause) { it.cause }.filterIsInstance<JacksonException>().firstOrNull() }

/**
 * Builds the refusal reported when a required request parameter is missing: `http.INVALID_PAYLOAD`, naming the
 * parameter in `parameters.parameter`.
 */
fun missingParameterError(name: String): F2Error = F2Error(
	id = UUID.randomUUID().toString(),
	timestamp = System.currentTimeMillis().toString(),
	message = "Missing parameter `$name`",
	code = HttpRefusal.INVALID_PAYLOAD.status,
	refusal = HttpRefusal.INVALID_PAYLOAD.refusal,
	parameters = mapOf(PARAMETER to name),
)

/** The `parameters` key naming the request parameter a refusal is about. */
const val PARAMETER: String = "parameter"
