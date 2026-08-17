package f2.spring.exception.config

import f2.spring.exception.findF2Exception
import f2.spring.exception.toAttributeMap
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webmvc.error.DefaultErrorAttributes
import org.springframework.web.context.request.WebRequest

class F2ErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(
        webRequest: WebRequest,
        options: ErrorAttributeOptions
    ): MutableMap<String, Any?> {
        val attributes = super.getErrorAttributes(
            webRequest,
            options.including(ErrorAttributeOptions.Include.MESSAGE)
        )
        getError(webRequest).findF2Exception()?.let { f2Exception ->
            attributes.putAll(f2Exception.error.toAttributeMap())
        }
        return attributes
    }
}
