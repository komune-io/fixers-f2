package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import java.util.UUID
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
        val body = mapOf(
            F2Error::id.name to ex.error.id,
            F2Error::code.name to ex.error.code,
            F2Error::message.name to ex.error.message,
            F2Error::timestamp.name to ex.error.timestamp,
        ).filterValues { it != null }
        val status = HttpStatus.resolve(ex.error.code) ?: HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(status).body(body)
    }

    @ExceptionHandler(KotlinInvalidNullException::class)
    fun handleKotlinNullException(ex: KotlinInvalidNullException): ResponseEntity<Map<String, Any?>> {
        val error = F2Error(
            id = UUID.randomUUID().toString(),
            timestamp = System.currentTimeMillis().toString(),
            message = "Missing parameter `${ex.kotlinPropertyName}`",
            code = 400,
        )
        val body = mapOf(
            F2Error::id.name to error.id,
            F2Error::code.name to error.code,
            F2Error::message.name to error.message,
            F2Error::timestamp.name to error.timestamp,
        ).filterValues { it != null }
        return ResponseEntity.badRequest().body(body)
    }
}
