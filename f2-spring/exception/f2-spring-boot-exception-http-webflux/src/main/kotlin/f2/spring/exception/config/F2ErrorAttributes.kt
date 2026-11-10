package f2.spring.exception.config

import f2.spring.exception.toF2ErrorBody
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webflux.error.DefaultErrorAttributes
import org.springframework.web.reactive.function.server.ServerRequest

/**
 * The F2 error body: when, where, the status an [f2.dsl.cqrs.exception.F2Exception] names, and the refusal code of
 * every error. Only a refusal tells the caller its message; no exception, trace or binding error ever travels,
 * whatever `server.error.include-*` asks for.
 */
class F2ErrorAttributes: DefaultErrorAttributes() {
    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any?> =
        super.getErrorAttributes(request, ErrorAttributeOptions.defaults())
            .toF2ErrorBody(getError(request), statusFromError = true)
}
