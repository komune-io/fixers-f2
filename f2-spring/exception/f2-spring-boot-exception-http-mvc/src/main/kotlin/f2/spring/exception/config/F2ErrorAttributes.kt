package f2.spring.exception.config

import f2.spring.exception.findF2Exception
import f2.spring.exception.refusalAttributes
import f2.spring.exception.unreadableBody
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webmvc.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.WebRequest

/**
 * The error body: Spring's attributes, the status an [f2.dsl.cqrs.exception.F2Exception] names, and the refusal
 * code of every error. Only a refusal tells the caller its message — never through `server.error.include-message`,
 * which would hand every client the message of every exception the application fails on.
 */
class F2ErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(
        webRequest: WebRequest,
        options: ErrorAttributeOptions
    ): MutableMap<String, Any?> {
        val attributes = super.getErrorAttributes(
            webRequest,
            options.excluding(ErrorAttributeOptions.Include.MESSAGE)
        )
        val error = getError(webRequest)
        val f2Exception = error.findF2Exception()
        val status = when {
            error?.unreadableBody() != null -> HttpStatus.BAD_REQUEST
            else -> f2Exception?.error?.code?.let(HttpStatus::resolve)
        }
        status?.let {
            attributes[STATUS] = it.value()
            attributes[ERROR] = it.reasonPhrase
        }
        val resolved = attributes[STATUS] as? Int ?: HttpStatus.INTERNAL_SERVER_ERROR.value()
        attributes.putAll(refusalAttributes(resolved, f2Exception?.error))
        return attributes
    }

    private companion object {
        const val STATUS = "status"
        const val ERROR = "error"
    }
}
