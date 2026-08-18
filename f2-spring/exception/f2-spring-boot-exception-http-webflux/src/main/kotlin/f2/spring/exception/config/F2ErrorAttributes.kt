package f2.spring.exception.config

import f2.spring.exception.findF2Exception
import f2.spring.exception.toAttributeMap
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webflux.error.DefaultErrorAttributes
import org.springframework.web.reactive.function.server.ServerRequest

class F2ErrorAttributes: DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any?> {
        val attributes = super.getErrorAttributes(request, options.including(ErrorAttributeOptions.Include.MESSAGE))
        getError(request).findF2Exception()?.let { f2Exception ->
            attributes.putAll(f2Exception.error.toAttributeMap())
        }
        return attributes
    }
}
