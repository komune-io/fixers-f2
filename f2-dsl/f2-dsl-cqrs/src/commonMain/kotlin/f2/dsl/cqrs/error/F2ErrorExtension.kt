package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception

fun F2ErrorDTO.asException(cause: Throwable? = null) = F2Exception(
    error = this,
    cause
)

fun F2ErrorDTO.throwException(cause: Throwable? = null) {
    throw asException(cause)
}

/** Whether [error] is a refusal: written for the caller, with a [F2ErrorDTO.message] meant to be read. */
fun isRefusal(error: F2ErrorDTO): Boolean = error.refusal != null

/** The code a caller tells [error] apart by: its refusal's, or the meaning of its status. */
fun refusalCode(error: F2ErrorDTO): String = error.refusal?.code ?: HttpRefusal.ofStatus(error.code).refusal

/** The `parameters` key naming the request parameter a refusal is about. */
const val PARAMETER_KEY: String = "parameter"

/**
 * The refusal of a request whose body could not be read: `http.INVALID_PAYLOAD`, naming the field the reader
 * stopped at in `parameters.parameter` when it knows it.
 */
fun invalidPayloadError(message: String, parameter: String? = null): F2Error = F2Error(
    message = message,
    code = HttpRefusal.INVALID_PAYLOAD.status,
    refusal = F2Refusal(
        code = HttpRefusal.INVALID_PAYLOAD.refusal,
        parameters = parameter?.let { mapOf(PARAMETER_KEY to it) }.orEmpty(),
    ),
)

/**
 * The refusal of a request missing a required parameter: `http.INVALID_PAYLOAD`, naming the parameter in
 * `parameters.parameter`.
 */
fun missingParameterError(name: String): F2Error = invalidPayloadError(
    message = "Missing parameter `$name`",
    parameter = name,
)
