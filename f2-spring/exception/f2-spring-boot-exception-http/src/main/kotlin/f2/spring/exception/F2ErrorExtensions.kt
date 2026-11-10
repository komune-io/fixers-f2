package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
import f2.dsl.cqrs.error.HttpRefusal
import f2.dsl.cqrs.error.PARAMETER_KEY
import f2.dsl.cqrs.exception.F2Exception
import java.time.Instant
import org.springframework.http.HttpStatus
import tools.jackson.core.JacksonException
import tools.jackson.databind.DatabindException

/** The error body's key for the refusal code. */
const val REFUSAL_ATTRIBUTE: String = "refusal"

/** `SimpleFunctionRegistry`'s message, or its prefix, when converting a function's input fails. */
private const val UNREADABLE_INPUT = "Failed to convert input"

/**
 * What an error body tells a caller beside the status: the refusal code always — the rule [error] names, or the
 * meaning of [status] — the [F2ErrorDTO.parameters] it gives, and, for a refusal only, its message and citers. Any
 * other error says nothing it was raised with: its message was not written for the caller, and may carry ids,
 * hosts or SQL.
 */
fun refusalAttributes(status: Int, error: F2ErrorDTO?): Map<String, Any> = buildMap {
	val refusal = error?.refusal
	put(REFUSAL_ATTRIBUTE, refusal ?: HttpRefusal.of(status).refusal)
	error?.parameters?.takeIf { it.isNotEmpty() }?.let { put(F2Error::parameters.name, it) }
	if (refusal != null) {
		put(F2Error::message.name, error.message)
		error.citers.takeIf { it.isNotEmpty() }?.let { put(F2Error::citers.name, it) }
	}
}

/**
 * The one error body F2 answers, wherever it is written — the error attributes, the MVC exception handler, a web
 * filter: when, where, the status and its phrase, then the [refusalAttributes] of [error].
 */
fun errorBody(path: String, status: HttpStatus, error: F2ErrorDTO?): MutableMap<String, Any?> =
	linkedMapOf<String, Any?>(
		TIMESTAMP_ATTRIBUTE to Instant.now().toString(),
		PATH_ATTRIBUTE to path,
		STATUS_ATTRIBUTE to status.value(),
		ERROR_ATTRIBUTE to status.reasonPhrase,
	).apply { putAll(refusalAttributes(status.value(), error)) }

/** A failure as its error body describes it: the status it answers, and the F2 error that names it, if any. */
class DescribedError(val status: HttpStatus, val error: F2ErrorDTO?)

/**
 * What this failure answers, [fallback] being the status the framework resolved for it: the status and error of
 * the [F2Exception] it carries, 400 for a request body nobody could read — naming the field the parser stopped
 * at, when it knows — or [fallback] and nothing else.
 */
fun Throwable?.describeError(fallback: HttpStatus): DescribedError {
	val unreadable = this?.unreadableBody()
	val f2Exception = findF2Exception()
	return when {
		unreadable != null -> DescribedError(HttpStatus.BAD_REQUEST, unreadable.asUnreadableBodyError())
		f2Exception != null ->
			DescribedError(HttpStatus.resolve(f2Exception.error.code) ?: fallback, f2Exception.error)
		else -> DescribedError(fallback, null)
	}
}

private fun Throwable.asUnreadableBodyError(): F2Error = F2Error(
	message = "Unreadable request body",
	code = HttpStatus.BAD_REQUEST.value(),
	parameters = (this as? DatabindException)?.path.orEmpty()
		.mapNotNull { it.propertyName }
		.takeIf { it.isNotEmpty() }
		?.let { mapOf(PARAMETER_KEY to it.joinToString(".")) }
		.orEmpty(),
)

private const val TIMESTAMP_ATTRIBUTE = "timestamp"
private const val PATH_ATTRIBUTE = "path"
private const val STATUS_ATTRIBUTE = "status"
private const val ERROR_ATTRIBUTE = "error"
private const val REQUEST_ID_ATTRIBUTE = "requestId"

/**
 * Spring's error [attributes] rewritten as the F2 [errorBody] for [failure]: Spring's status and path, its
 * request id when it gives one, and nothing else of it — no message, exception, trace or binding errors, whatever
 * `server.error.include-*` asks for.
 */
fun Map<String, Any?>.toF2ErrorBody(failure: Throwable?, statusFromError: Boolean): MutableMap<String, Any?> {
	val resolved = (this[STATUS_ATTRIBUTE] as? Int)?.let(HttpStatus::resolve) ?: HttpStatus.INTERNAL_SERVER_ERROR
	val described = failure.describeError(resolved)
	val status = if (statusFromError) described.status else resolved
	return errorBody(this[PATH_ATTRIBUTE]?.toString().orEmpty(), status, described.error).also { body ->
		this[REQUEST_ID_ATTRIBUTE]?.let { body[REQUEST_ID_ATTRIBUTE] = it }
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
 * The failure of a request body nobody could read, or `null`. A value of the wrong type is a binding error F2
 * answers 400 itself, but a text cut short is a parser error, which Spring Cloud Function hands over wrapped in an
 * `IllegalStateException("Failed to convert input")`; an input that converts to nothing fails its
 * `Assert.notNull` with `IllegalArgumentException("Failed to convert input: …")`. Only those wrappers count: a
 * parser failing on something the server read itself is a server error.
 */
fun Throwable.unreadableBody(): Throwable? = when {
	this is IllegalStateException && message == UNREADABLE_INPUT ->
		generateSequence(cause) { it.cause }.filterIsInstance<JacksonException>().firstOrNull()
	this is IllegalArgumentException && message.orEmpty().startsWith("$UNREADABLE_INPUT: ") -> this
	else -> null
}
