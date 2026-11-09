package f2.spring.exception.config

import f2.spring.exception.completeWithRefusal
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webmvc.error.DefaultErrorAttributes
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
        attributes.completeWithRefusal(getError(webRequest))
        return attributes
    }
}
