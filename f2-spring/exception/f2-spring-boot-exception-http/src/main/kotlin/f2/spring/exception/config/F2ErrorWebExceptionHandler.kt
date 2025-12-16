package f2.spring.exception.config

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.webflux.autoconfigure.error.DefaultErrorWebExceptionHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.result.view.ViewResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import tools.jackson.module.kotlin.KotlinInvalidNullException
import java.util.UUID

@Configuration
@Suppress("MagicNumber")
@Order(-2)
class F2ErrorWebExceptionHandler(
    applicationContext: ApplicationContext,
    webProperties: WebProperties,
    serverCodecConfigurer: ServerCodecConfigurer,
    viewResolvers: ObjectProvider<ViewResolver>,
): DefaultErrorWebExceptionHandler(
    F2ErrorAttributes(),
    webProperties.resources,
    webProperties.error,
    applicationContext
) {
    companion object {
        private const val INTERNAL_ERROR = 500
    }
    init {
        setViewResolvers(viewResolvers.toList())
        setMessageWriters(serverCodecConfigurer.writers)
        setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun handle(
        exchange: ServerWebExchange, throwable: Throwable
    ): Mono<Void> {
        val cause = throwable.cause
        return if (cause is F2Exception) {
             super.handle(exchange, cause)
        }
        else if(cause is KotlinInvalidNullException) {
            super.handle(exchange,  F2Exception(error = F2Error(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis().toString(),
                message = "Missing parameter `${cause.kotlinPropertyName}`",
                code = 400,
            )))
        }
        else {
            return super.handle(exchange, throwable)
        }
    }

    override fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        val error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL))
        val status: Int = error[F2Error::code.name] as Int? ?: INTERNAL_ERROR
        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue<Map<String, Any>>(error as Map<String, Any>))
    }
}
