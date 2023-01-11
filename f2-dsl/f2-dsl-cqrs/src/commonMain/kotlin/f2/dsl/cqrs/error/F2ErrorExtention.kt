package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception

inline fun F2ErrorDTO.asException(cause: Throwable? = null) = F2Exception(
    error = this,
    cause
)

inline fun F2ErrorDTO.throwException(cause: Throwable? = null) {
    throw asException(cause)
}
