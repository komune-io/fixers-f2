package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.F2HttpException
import f2.spring.exception.missingParameterError
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
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import tools.jackson.module.kotlin.KotlinInvalidNullException

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

    @Suppress("ForbiddenVoid") // overrides the Java signature of ErrorWebExceptionHandler
    override fun handle(
        exchange: ServerWebExchange, throwable: Throwable
    ): Mono<Void> {
        val resolved = resolveException(throwable)
        return super.handle(exchange, resolved)
    }

    private fun resolveException(throwable: Throwable): Throwable {
        val f2Http = generateSequence(throwable) { it.cause }
            .filterIsInstance<F2HttpException>()
            .firstOrNull()
        if (f2Http != null) {
            return ResponseStatusException(f2Http.status, f2Http.message, f2Http)
        }

        // WebFlux wraps body-decoding failures multiple levels deep, e.g.
        // ServerWebInputException -> DecodingException -> KotlinInvalidNullException -
        // a single `.cause` check misses it. Search the whole chain instead.
        val classifiedCause = throwable.causeChain()
            .firstOrNull { it is F2Exception || it is KotlinInvalidNullException }
        return when (val cause = classifiedCause) {
            is F2Exception -> cause
            is KotlinInvalidNullException -> F2Exception(error = missingParameterError(cause.kotlinPropertyName))
            else -> throwable
        }
    }

    private fun Throwable.causeChain(): Sequence<Throwable> = generateSequence(cause) { it.cause }

    override fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        val error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL))
        // F2ErrorAttributes resolves "status": an F2Exception's code, a body nobody could read, or what
        // DefaultErrorAttributes derives from the throwable (ResponseStatusException, ErrorResponse).
        val status: Int = error["status"] as Int? ?: INTERNAL_ERROR
        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(error.filterValues { it != null }))
    }
}
