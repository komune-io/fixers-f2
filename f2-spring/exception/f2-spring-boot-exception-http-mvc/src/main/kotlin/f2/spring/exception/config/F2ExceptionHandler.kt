package f2.spring.exception.config

import f2.dsl.cqrs.error.F2ErrorDTO
import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.missingParameterError
import f2.spring.exception.refusalAttributes
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.module.kotlin.KotlinInvalidNullException

private const val EXCEPTION_HANDLER_ORDER = -2

/**
 * Answers an [F2Exception] with the status its error names and the body [F2ErrorAttributes] gives every error:
 * the refusal code, and a refusal's message, parameters and citers — nothing else the exception was raised with.
 */
@Configuration
@RestControllerAdvice
@Order(EXCEPTION_HANDLER_ORDER)
class F2ExceptionHandler {

    @ExceptionHandler(F2Exception::class)
    fun handleF2Exception(ex: F2Exception): ResponseEntity<Map<String, Any>> = ex.error.toResponse()

    @ExceptionHandler(KotlinInvalidNullException::class)
    fun handleKotlinNullException(ex: KotlinInvalidNullException): ResponseEntity<Map<String, Any>> =
        missingParameterError(ex.kotlinPropertyName).toResponse()

    private fun F2ErrorDTO.toResponse(): ResponseEntity<Map<String, Any>> {
        val status = HttpStatus.resolve(code) ?: HttpStatus.INTERNAL_SERVER_ERROR
        val body = mapOf("status" to status.value(), "error" to status.reasonPhrase) +
            refusalAttributes(status.value(), this)
        return ResponseEntity.status(status).body(body)
    }
}
