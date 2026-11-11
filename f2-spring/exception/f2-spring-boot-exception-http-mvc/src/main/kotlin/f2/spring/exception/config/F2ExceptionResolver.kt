package f2.spring.exception.config

import f2.dsl.cqrs.error.F2ErrorDTO
import f2.spring.exception.describeError
import f2.spring.exception.errorBody
import f2.spring.exception.httpStatus
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.http.MediaType
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import tools.jackson.databind.json.JsonMapper

/**
 * Answers every failure that carries an F2 error — an [f2.dsl.cqrs.exception.F2Exception] or a missing parameter
 * at any depth of its cause chain, or a request body nobody could read ([describeError]) — with the status that
 * error names and the F2 error body, ahead of every `@ExceptionHandler`: one of a wrapper's own type would
 * otherwise win, and the status would be the wrapper's 500.
 *
 * The body is written with [jsonMapper], the application's own: the bean every F2 error writer uses.
 *
 * Any other failure is left alone ([resolveException] answers `null`): the application's own exception handlers
 * and Spring's resolution see it as if this resolver did not exist.
 */
class F2ExceptionResolver(
    private val jsonMapper: JsonMapper,
) : HandlerExceptionResolver, Ordered {

    override fun getOrder(): Int = ORDER

    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception,
    ): ModelAndView? {
        val error = ex.describeError()
        // a response already on its way cannot take another status nor a second body: Spring's resolution logs it
        if (error == null || response.isCommitted) return null
        response.writeF2Error(request.requestURI, error, jsonMapper)
        return ModelAndView()
    }

    companion object {
        /** After `DefaultErrorAttributes`, which only records the error, and before every other resolver. */
        const val ORDER: Int = Ordered.HIGHEST_PRECEDENCE + 1
    }
}

/** Answers this response with the status of [error] and the F2 error body at [path], written with [jsonMapper]. */
internal fun HttpServletResponse.writeF2Error(path: String, error: F2ErrorDTO, jsonMapper: JsonMapper) {
    val status = error.httpStatus()
    this.status = status.value()
    contentType = MediaType.APPLICATION_JSON_VALUE
    characterEncoding = Charsets.UTF_8.name()
    outputStream.write(jsonMapper.writeValueAsBytes(errorBody(path, status, error)))
}
