package f2.dsl.cqrs.exception

import f2.dsl.cqrs.error.F2ErrorDTO

open class F2Exception(
    val error: F2ErrorDTO,
    cause: Throwable? = null
) : RuntimeException(error.message, cause)
