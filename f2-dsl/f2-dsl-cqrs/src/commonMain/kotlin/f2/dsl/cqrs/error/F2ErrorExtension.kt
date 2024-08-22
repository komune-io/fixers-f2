package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception

fun F2ErrorDTO.asException() = asException(null)

fun F2ErrorDTO.asException(cause: Throwable? = null) = F2Exception(
    error = this,
    cause
)

fun F2ErrorDTO.throwException() = throwException(null)

fun F2ErrorDTO.throwException(cause: Throwable? = null) {
    throw asException(cause)
}


fun Exception.asError() = F2Error(
    message = this.message ?: "Unknown error"
)
