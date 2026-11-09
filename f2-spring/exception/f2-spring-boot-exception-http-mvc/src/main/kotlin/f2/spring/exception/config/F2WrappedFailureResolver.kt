package f2.spring.exception.config

import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.unreadableBody
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.http.MediaType
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinInvalidNullException

/**
 * Answers what Spring Cloud Function wraps in an `IllegalStateException` or an `IllegalArgumentException` — an
 * [F2Exception] or a missing parameter at any depth, as the WebFlux handler finds them, or an input it could not
 * convert — with the status they name and the F2 error body, ahead of every `@ExceptionHandler`: one of the
 * wrapper's own type would otherwise win over [F2ExceptionHandler]'s.
 *
 * Any other failure is left alone ([resolveException] answers `null`): the application's own exception handlers
 * and Spring's resolution see it as if this resolver did not exist.
 */
class F2WrappedFailureResolver : HandlerExceptionResolver, Ordered {

    override fun getOrder(): Int = ORDER

    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception,
    ): ModelAndView? {
        val failure = ex.wrappedF2Failure() ?: return null
        val answer = f2ErrorResponse(failure, request.requestURI)
        response.status = answer.statusCode.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        response.outputStream.write(JsonMapper.shared().writeValueAsBytes(answer.body))
        return ModelAndView()
    }

    companion object {
        /** After `DefaultErrorAttributes`, which only records the error, and before every other resolver. */
        const val ORDER: Int = Ordered.HIGHEST_PRECEDENCE + 1
    }
}

/** The F2 failure this wrapper carries, or `null` when it is not a wrapper or carries nothing of F2. */
internal fun Throwable.wrappedF2Failure(): Throwable? {
    if (this !is IllegalStateException && this !is IllegalArgumentException) return null
    val classified = generateSequence(cause) { it.cause }
        .firstOrNull { it is F2Exception || it is KotlinInvalidNullException }
    return when (classified) {
        is F2Exception -> classified
        is KotlinInvalidNullException -> F2Exception(error = missingParameterError(classified.kotlinPropertyName))
        else -> takeIf { unreadableBody() != null }
    }
}
