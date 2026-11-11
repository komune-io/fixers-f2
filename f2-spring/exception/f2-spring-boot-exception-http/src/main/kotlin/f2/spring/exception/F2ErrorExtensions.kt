package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2ErrorDTO
import f2.dsl.cqrs.error.invalidPayloadError
import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.error.refusalCode
import f2.dsl.cqrs.exception.F2Exception
import java.time.Instant
import org.springframework.core.codec.DecodingException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.server.ServerWebInputException
import tools.jackson.core.JacksonException
import tools.jackson.databind.DatabindException
import tools.jackson.module.kotlin.KotlinInvalidNullException

/** The error body's key for the refusal code. */
const val REFUSAL_ATTRIBUTE: String = "refusal"

// `SimpleFunctionRegistry`'s message, or its prefix, when converting a function's input fails.
private const val UNREADABLE_INPUT = "Failed to convert input"

// Spring Cloud Function's JacksonMapper, when the function input it converts fails.
private const val UNREADABLE_JSON = "Failed to convert JSON to "

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
    this[REFUSAL_ATTRIBUTE] = refusalCode(error)
    val refusal = error.refusal
    if (refusal != null) {
        this[MESSAGE_ATTRIBUTE] = error.message
        if (refusal.parameters.isNotEmpty()) this[PARAMETERS_ATTRIBUTE] = refusal.parameters
        if (refusal.citers.isNotEmpty()) this[CITERS_ATTRIBUTE] = refusal.citers
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

/** The status this error answers: its code, or 500 when its code is no HTTP error status (a 2xx, a 3xx, 999…). */
fun F2ErrorDTO.httpStatus(): HttpStatus =
    HttpStatus.resolve(code)?.takeIf { it.isError } ?: HttpStatus.INTERNAL_SERVER_ERROR

/**
 * The F2 error this failure carries — the one rule every F2 error writer reads: anywhere in its cause chain, an
 * [F2Exception]'s error; under the reading of the request's input (Spring Cloud Function's "Failed to convert
 * input", a `ServerWebInputException`, a `DecodingException`, an `HttpMessageNotReadableException`), a missing
 * required parameter; else a request body nobody could read, naming the field the parser stopped at when it knows
 * it. `null` when the failure carries nothing of F2: the framework's status stands, and the caller learns its
 * meaning only. A missing field the server met reading its own data is the server's failure, a 500.
 */
fun Throwable?.describeError(): F2ErrorDTO? {
    val chain = generateSequence(this) { it.cause }.toList()
    val refused = chain.firstNotNullOfOrNull { (it as? F2Exception)?.error }
    val missing = chain.dropWhile { !it.readsRequestInput() }
        .firstNotNullOfOrNull { (it as? KotlinInvalidNullException)?.kotlinPropertyName }
    return missing?.let(::missingParameterError) ?: refused ?: this?.unreadableBody()?.unreadableBodyError()
}

private fun Throwable.readsRequestInput(): Boolean =
    this is ServerWebInputException ||
        this is DecodingException ||
        this is HttpMessageNotReadableException ||
        this is UnreadableBodyException ||
        isInputConversionWrapper()

// Spring Cloud Function's wrappers of a function input it could not convert: the registry's, and its JSON mapper's.
private fun Throwable.isInputConversionWrapper(): Boolean =
    this is IllegalStateException && (message == UNREADABLE_INPUT || message.orEmpty().startsWith(UNREADABLE_JSON)) ||
        this is IllegalArgumentException && message.orEmpty().startsWith("$UNREADABLE_INPUT: ")

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
    val body = errorBody(this[PATH_ATTRIBUTE]?.toString().orEmpty(), status, error)
    this[REQUEST_ID_ATTRIBUTE]?.let { body[REQUEST_ID_ATTRIBUTE] = it }
    return body
}

/**
 * The failure of a request body nobody could read, or `null`. A value of the wrong type is a binding error F2
 * answers 400 itself, but a text cut short is a parser error, which Spring Cloud Function hands over wrapped in an
 * `IllegalStateException("Failed to convert input")`; an input that converts to nothing fails its
 * `Assert.notNull` with `IllegalArgumentException("Failed to convert input: …")`. Only those wrappers count: a
 * parser failing on something the server read itself is a server error.
 */
fun Throwable.unreadableBody(): Throwable? = when {
    this is IllegalStateException && isInputConversionWrapper() ->
        generateSequence(cause) { it.cause }.filterIsInstance<JacksonException>().firstOrNull()
    this is IllegalArgumentException && isInputConversionWrapper() -> this
    else -> null
}
