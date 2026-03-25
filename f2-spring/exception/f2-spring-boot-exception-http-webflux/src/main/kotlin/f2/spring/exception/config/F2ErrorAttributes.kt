package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webflux.error.DefaultErrorAttributes
import org.springframework.web.reactive.function.server.ServerRequest

class F2ErrorAttributes: DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any?> {
        val attributes = super.getErrorAttributes(request, options.including(ErrorAttributeOptions.Include.MESSAGE))
        val exception = getError(request)
        if (exception is F2Exception) {
            attributes[F2Error::id.name] = exception.error.id
            attributes[F2Error::code.name] = exception.error.code
            attributes[F2Error::message.name] = exception.error.message
            attributes[F2Error::timestamp.name] = exception.error.timestamp
        }
        return attributes
    }
}
