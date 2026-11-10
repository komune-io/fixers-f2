package f2.spring.exception.config

import f2.dsl.cqrs.error.F2ErrorDTO
import f2.spring.exception.errorBody
import f2.spring.exception.httpStatus
import org.springframework.http.MediaType
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import tools.jackson.databind.json.JsonMapper

/**
 * Answers this exchange with the status of [error] and the F2 [errorBody] every other error gets — when, where,
 * the status, the refusal code, the request id — from where the error handler cannot see it: a web filter, or the
 * security chain's entry point and access-denied handler. Only a refusal tells its message.
 *
 * The body is written with [jsonMapper], the application's own: the bean every F2 error writer uses.
 */
@Suppress("ForbiddenVoid") // the Mono<Void> of ServerHttpResponse.writeWith, and of the security handlers
fun ServerWebExchange.writeRefusal(error: F2ErrorDTO, jsonMapper: JsonMapper): Mono<Void> {
    val status = error.httpStatus()
    val body = errorBody(request.path.value(), status, error).apply { put("requestId", request.id) }
    response.statusCode = status
    response.headers.contentType = MediaType.APPLICATION_JSON
    return response.writeWith(Mono.fromSupplier {
        response.bufferFactory().wrap(jsonMapper.writeValueAsBytes(body))
    })
}
