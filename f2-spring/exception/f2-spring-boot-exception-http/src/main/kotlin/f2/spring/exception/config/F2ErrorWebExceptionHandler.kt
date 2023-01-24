package f2.spring.exception.config

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import java.util.UUID
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.result.view.ViewResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Configuration
@Order(-2)
class F2ErrorWebExceptionHandler(
    applicationContext: ApplicationContext,
    webProperties: WebProperties,
    serverCodecConfigurer: ServerCodecConfigurer,
    viewResolvers: ObjectProvider<ViewResolver>,
    serverProperties: ServerProperties,
): DefaultErrorWebExceptionHandler(
    F2ErrorAttributes(),
    webProperties.resources,
    serverProperties.error,
    applicationContext
) {
    init {
        setViewResolvers(viewResolvers.toList())
        setMessageWriters(serverCodecConfigurer.writers)
        setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> {
        return super.getRoutingFunction(errorAttributes)
    }

    override fun handle(exchange: ServerWebExchange, throwable: Throwable): Mono<Void> {
        val cause = throwable.cause
        if (cause is F2Exception) {
            return super.handle(exchange, cause)
        } else if(cause is MissingKotlinParameterException) {
            return super.handle(exchange,  F2Exception(error = F2Error(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis().toString(),
                message = "Missing parameter `${cause.parameter.name!!}`",
                code = 400,
            )))
        }
        return super.handle(exchange, throwable)
    }

    override fun renderErrorResponse(request: ServerRequest?): Mono<ServerResponse?>? {
        val error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL))
        val status: Int =  error[F2Error::code.name] as Int? ?: 500;
        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(error))
    }
}
