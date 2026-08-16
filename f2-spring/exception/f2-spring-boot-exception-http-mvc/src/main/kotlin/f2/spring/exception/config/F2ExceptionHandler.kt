package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.missingParameterError
import f2.spring.exception.toAttributeMap
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tools.jackson.module.kotlin.KotlinInvalidNullException

private const val EXCEPTION_HANDLER_ORDER = -2

@Configuration
@RestControllerAdvice
@Order(EXCEPTION_HANDLER_ORDER)
class F2ExceptionHandler {

    @ExceptionHandler(F2Exception::class)
    fun handleF2Exception(ex: F2Exception): ResponseEntity<Map<String, Any?>> {
        val body = ex.error.toAttributeMap().filterValues { it != null }
        val status = HttpStatus.resolve(ex.error.code) ?: HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(status).body(body)
    }

    @ExceptionHandler(KotlinInvalidNullException::class)
    fun handleKotlinNullException(ex: KotlinInvalidNullException): ResponseEntity<Map<String, Any?>> {
        val body = missingParameterError(ex.kotlinPropertyName).toAttributeMap()
        return ResponseEntity.badRequest().body(body)
    }
}
