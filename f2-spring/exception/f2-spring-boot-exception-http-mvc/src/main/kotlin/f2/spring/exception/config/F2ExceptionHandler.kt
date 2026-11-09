package f2.spring.exception.config

import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.describeError
import f2.spring.exception.errorBody
import f2.spring.exception.unreadableBody
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.module.kotlin.KotlinInvalidNullException

private const val EXCEPTION_HANDLER_ORDER = -2

/**
 * Answers an [F2Exception] — thrown, or wrapped once by Spring Cloud Function — and a request body nobody could
 * read with the status they name and the F2 [errorBody]: the response's status and the body's always agree.
 * Registered by [F2ExceptionWebMvcAutoConfiguration].
 */
@RestControllerAdvice
@Order(EXCEPTION_HANDLER_ORDER)
class F2ExceptionHandler {

    @ExceptionHandler(F2Exception::class)
    fun handleF2Exception(ex: F2Exception, request: HttpServletRequest): ResponseEntity<Map<String, Any?>> =
        answer(ex, request)

    /**
     * Spring Cloud Function's wrappers, which win over [handleF2Exception] by their own type: an input it could not
     * convert, an [F2Exception] or a missing parameter it wrapped — at any depth, as the WebFlux handler finds
     * them. Any other failure is thrown back unhandled, to Spring's own resolution and error page.
     */
    @ExceptionHandler(IllegalStateException::class, IllegalArgumentException::class)
    fun handleWrapped(ex: RuntimeException, request: HttpServletRequest): ResponseEntity<Map<String, Any?>> {
        val classified = generateSequence(ex.cause) { it.cause }
            .firstOrNull { it is F2Exception || it is KotlinInvalidNullException }
        return when {
            classified is F2Exception -> answer(classified, request)
            classified is KotlinInvalidNullException -> handleKotlinNullException(classified, request)
            ex.unreadableBody() != null -> answer(ex, request)
            else -> throw ex
        }
    }

    @ExceptionHandler(KotlinInvalidNullException::class)
    fun handleKotlinNullException(
        ex: KotlinInvalidNullException,
        request: HttpServletRequest,
    ): ResponseEntity<Map<String, Any?>> =
        answer(F2Exception(error = missingParameterError(ex.kotlinPropertyName)), request)

    private fun answer(failure: Throwable, request: HttpServletRequest): ResponseEntity<Map<String, Any?>> {
        val described = failure.describeError(HttpStatus.INTERNAL_SERVER_ERROR)
        return ResponseEntity.status(described.status)
            .body(errorBody(request.requestURI, described.status, described.error))
    }
}
