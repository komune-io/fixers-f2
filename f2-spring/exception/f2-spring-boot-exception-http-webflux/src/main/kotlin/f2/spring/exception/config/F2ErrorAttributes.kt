package f2.spring.exception.config

import f2.spring.exception.completeWithRefusal
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webflux.error.DefaultErrorAttributes
import org.springframework.web.reactive.function.server.ServerRequest

/**
 * The error body: Spring's attributes, the status an [f2.dsl.cqrs.exception.F2Exception] names, and the refusal
 * code of every error. Only a refusal tells the caller its message — never through `server.error.include-message`,
 * which would hand every client the message of every exception the application fails on.
 */
class F2ErrorAttributes: DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any?> {
        val attributes = super.getErrorAttributes(request, options.excluding(ErrorAttributeOptions.Include.MESSAGE))
        attributes.completeWithRefusal(getError(request))
        return attributes
    }
}
