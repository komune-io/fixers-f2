package f2.spring.exception.config

import f2.dsl.cqrs.error.HttpRefusal
import f2.spring.exception.REFUSAL_ATTRIBUTE
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import tools.jackson.databind.json.JsonMapper

/**
 * Answers this exchange with [status] and the body [F2ErrorAttributes] gives every other error, from where the
 * error handler cannot see it — a web filter, or the security chain's entry point and access-denied handler.
 *
 * The body names [refusal], the meaning of [status] unless a rule of the application's own is given. [message] is
 * for a sentence written to be read by the caller, and for nothing else.
 */
// The Mono<Void> of ServerHttpResponse.writeWith, and of the security handlers that return it.
@Suppress("ForbiddenVoid")
fun ServerWebExchange.writeRefusal(
    status: HttpStatus,
    message: String? = null,
    refusal: String = HttpRefusal.of(status.value()).refusal,
): Mono<Void> {
    val body = buildMap {
        put("path", request.path.pathWithinApplication().value())
        put("status", status.value())
        put("error", status.reasonPhrase)
        put(REFUSAL_ATTRIBUTE, refusal)
        message?.let { put("message", it) }
    }
    response.statusCode = status
    response.headers.contentType = MediaType.APPLICATION_JSON
    return response.writeWith(Mono.fromSupplier {
        response.bufferFactory().wrap(JsonMapper.shared().writeValueAsBytes(body))
    })
}
