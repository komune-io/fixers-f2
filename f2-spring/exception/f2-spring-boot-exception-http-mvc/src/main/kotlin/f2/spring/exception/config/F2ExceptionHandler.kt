package f2.spring.exception.config

import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.describeError
import f2.spring.exception.errorBody
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.module.kotlin.KotlinInvalidNullException

private const val EXCEPTION_HANDLER_ORDER = -2

/**
 * Answers an [F2Exception] and a missing parameter with the status they name and the F2 [errorBody]: the
 * response's status and the body's always agree. Their wrapped forms are [F2WrappedFailureResolver]'s.
 * Registered by [F2ExceptionWebMvcAutoConfiguration].
 */
@RestControllerAdvice
@Order(EXCEPTION_HANDLER_ORDER)
class F2ExceptionHandler {

    @ExceptionHandler(F2Exception::class)
    fun handleF2Exception(ex: F2Exception, request: HttpServletRequest): ResponseEntity<Map<String, Any?>> =
        f2ErrorResponse(ex, request.requestURI)

    @ExceptionHandler(KotlinInvalidNullException::class)
    fun handleKotlinNullException(
        ex: KotlinInvalidNullException,
        request: HttpServletRequest,
    ): ResponseEntity<Map<String, Any?>> =
        f2ErrorResponse(F2Exception(error = missingParameterError(ex.kotlinPropertyName)), request.requestURI)
}

/** The F2 answer to [failure]: the status it names, and the F2 [errorBody] at [path]. */
internal fun f2ErrorResponse(failure: Throwable, path: String): ResponseEntity<Map<String, Any?>> {
    val described = failure.describeError(HttpStatus.INTERNAL_SERVER_ERROR)
    return ResponseEntity.status(described.status).body(errorBody(path, described.status, described.error))
}
