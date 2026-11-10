package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
import f2.dsl.cqrs.error.invalidPayloadError
import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.error.refusalCode
import f2.dsl.cqrs.exception.F2Exception
import java.time.Instant
import org.springframework.http.HttpStatus
import tools.jackson.core.JacksonException
import tools.jackson.databind.DatabindException
import tools.jackson.module.kotlin.KotlinInvalidNullException

/** The error body's key for the refusal code. */
const val REFUSAL_ATTRIBUTE: String = "refusal"

/** `SimpleFunctionRegistry`'s message, or its prefix, when converting a function's input fails. */
private const val UNREADABLE_INPUT = "Failed to convert input"

private const val TIMESTAMP_ATTRIBUTE = "timestamp"
private const val PATH_ATTRIBUTE = "path"
private const val STATUS_ATTRIBUTE = "status"
private const val ERROR_ATTRIBUTE = "error"
private const val REQUEST_ID_ATTRIBUTE = "requestId"
private const val MESSAGE_ATTRIBUTE = "message"
private const val PARAMETERS_ATTRIBUTE = "parameters"
private const val CITERS_ATTRIBUTE = "citers"

/**
 * What an error body tells a caller beside the status: the [refusalCode] of [error] always — the rule it names, or
 * the meaning of its status — and, for a refusal only, its message, parameters and citers. Any other error says
 * nothing it was raised with: its message was not written for the caller, and may carry ids, hosts or SQL.
 */
fun refusalAttributes(error: F2ErrorDTO): Map<String, Any> = buildMap {
	put(REFUSAL_ATTRIBUTE, refusalCode(error))
	error.refusal?.let { refusal ->
		put(MESSAGE_ATTRIBUTE, error.message)
		refusal.parameters.takeIf { it.isNotEmpty() }?.let { put(PARAMETERS_ATTRIBUTE, it) }
		refusal.citers.takeIf { it.isNotEmpty() }?.let { put(CITERS_ATTRIBUTE, it) }
	}
}

/**
 * The one error body F2 answers, wherever it is written — the error attributes, the MVC resolver, a web filter or
 * the security chain: when, where, [status] and its phrase, then the [refusalAttributes] of [error].
 */
fun errorBody(path: String, status: HttpStatus, error: F2ErrorDTO): MutableMap<String, Any?> =
	linkedMapOf<String, Any?>(
		TIMESTAMP_ATTRIBUTE to Instant.now().toString(),
		PATH_ATTRIBUTE to path,
		STATUS_ATTRIBUTE to status.value(),
		ERROR_ATTRIBUTE to status.reasonPhrase,
	).apply { putAll(refusalAttributes(error)) }

/** The error a failure the framework answered with [status] gives the caller: the meaning of that status only. */
fun statusError(status: HttpStatus): F2Error = F2Error(message = status.reasonPhrase, code = status.value())

/** The status this error answers: its code, or 500 when its code is no HTTP status. */
fun F2ErrorDTO.httpStatus(): HttpStatus = HttpStatus.resolve(code) ?: HttpStatus.INTERNAL_SERVER_ERROR

/**
 * The F2 error this failure carries — the one rule every F2 error writer reads: anywhere in its cause chain, an
 * [F2Exception]'s error or a missing required parameter; else a request body nobody could read, naming the field
 * the parser stopped at when it knows it. `null` when the failure carries nothing of F2: the framework's status
 * stands, and the caller learns its meaning only.
 */
fun Throwable?.describeError(): F2ErrorDTO? {
	if (this == null) return null
	return generateSequence(this) { it.cause }.firstNotNullOfOrNull { failure ->
		when (failure) {
			is F2Exception -> failure.error
			is KotlinInvalidNullException -> missingParameterError(failure.kotlinPropertyName)
			else -> null
		}
	} ?: unreadableBody()?.unreadableBodyError()
}

/**
 * The refusal of a request body nobody could read because of this parser failure: `http.INVALID_PAYLOAD`, naming
 * the field the parser stopped at when it knows it, and nothing it was parsed with.
 */
fun Throwable.unreadableBodyError(): F2Error = invalidPayloadError(
	message = "Unreadable request body",
	parameter = (this as? DatabindException)?.path.orEmpty()
		.mapNotNull { it.propertyName }
		.takeIf { it.isNotEmpty() }
		?.joinToString("."),
)

/**
 * Spring's error attributes rewritten as the F2 [errorBody] for [failure]: Spring's status and path, its request
 * id when it gives one, and nothing else of it — no message, exception, trace or binding errors, whatever
 * `server.error.include-*` asks for. With [statusFromError], the body answers the status of the F2 error the
 * failure carries; without it, the status Spring resolved, the one the response already carries.
 */
fun Map<String, Any?>.toF2ErrorBody(failure: Throwable?, statusFromError: Boolean): MutableMap<String, Any?> {
	val resolved = (this[STATUS_ATTRIBUTE] as? Int)?.let(HttpStatus::resolve) ?: HttpStatus.INTERNAL_SERVER_ERROR
	val error = failure.describeError() ?: statusError(resolved)
	val status = if (statusFromError) error.httpStatus() else resolved
	return errorBody(this[PATH_ATTRIBUTE]?.toString().orEmpty(), status, error).also { body ->
		this[REQUEST_ID_ATTRIBUTE]?.let { body[REQUEST_ID_ATTRIBUTE] = it }
	}
}

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
