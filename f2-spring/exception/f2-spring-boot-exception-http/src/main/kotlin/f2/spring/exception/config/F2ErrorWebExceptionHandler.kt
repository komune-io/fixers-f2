package f2.spring.exception.config

import f2.spring.exception.F2Exception
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.function.server.RouterFunction
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
    serverProperties: ServerProperties
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
        if (throwable.cause is F2Exception) {
            return super.handle(exchange, throwable.cause!!)
        }
        return super.handle(exchange, throwable)
    }
}
