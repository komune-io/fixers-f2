package f2.spring.exception.config

import f2.spring.exception.toF2ErrorBody
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webmvc.error.DefaultErrorAttributes
import org.springframework.web.context.request.WebRequest

/**
 * The F2 error body: when, where, the status the servlet answers, and the refusal code of every error. Only a
 * refusal tells the caller its message; no exception, trace or binding error ever travels, whatever
 * `server.error.include-*` asks for.
 *
 * The status stays the servlet's, the one the response carries: an [f2.dsl.cqrs.exception.F2Exception] answers its
 * own through [F2ExceptionHandler], before the error page is ever rendered.
 */
class F2ErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(webRequest: WebRequest, options: ErrorAttributeOptions): MutableMap<String, Any?> =
        super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults())
            .toF2ErrorBody(getError(webRequest), statusFromError = false)
}
