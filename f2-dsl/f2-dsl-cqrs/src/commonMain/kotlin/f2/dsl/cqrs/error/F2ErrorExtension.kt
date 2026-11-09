package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception
import kotlin.js.JsExport
import kotlin.jvm.JvmName

fun F2ErrorDTO.asException(cause: Throwable? = null) = F2Exception(
    error = this,
    cause
)

fun F2ErrorDTO.throwException(cause: Throwable? = null) {
    throw asException(cause)
}

fun Exception.asError() = F2Error(
    message = this.message ?: "Unknown error"
)

/** Whether this error is a refusal: written for the caller, with a [F2ErrorDTO.message] meant to be read. */
@get:JvmName("isRefusalExtension")
val F2ErrorDTO.isRefusal: Boolean get() = isRefusal(this)

/** The code a caller tells this error apart by: its refusal's, or the meaning of its status. */
@get:JvmName("refusalCodeExtension")
val F2ErrorDTO.refusalCode: String get() = refusalCode(this)

/** Whether [error] is a refusal: written for the caller, with a [F2ErrorDTO.message] meant to be read. */
@JsExport
fun isRefusal(error: F2ErrorDTO): Boolean = error.refusal != null

/** The code a caller tells [error] apart by: its refusal's, or the meaning of its status. */
@JsExport
fun refusalCode(error: F2ErrorDTO): String = error.refusal ?: HttpRefusal.of(error.code).refusal

/** The `parameters` key naming the request parameter a refusal is about. */
const val PARAMETER_KEY: String = "parameter"

/**
 * The refusal of a request missing a required parameter: `http.INVALID_PAYLOAD`, naming the parameter in
 * `parameters.parameter`.
 */
fun missingParameterError(name: String): F2Error = F2Error(
    message = "Missing parameter `$name`",
    code = HttpRefusal.INVALID_PAYLOAD.status,
    refusal = HttpRefusal.INVALID_PAYLOAD.refusal,
    parameters = mapOf(PARAMETER_KEY to name),
)
