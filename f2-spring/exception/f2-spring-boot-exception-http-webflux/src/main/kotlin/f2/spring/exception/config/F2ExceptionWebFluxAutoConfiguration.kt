package f2.spring.exception.config

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.webflux.autoconfigure.error.ErrorWebFluxAutoConfiguration
import org.springframework.boot.webflux.error.ErrorAttributes
import org.springframework.boot.webflux.error.ErrorWebExceptionHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.result.view.ViewResolver

/**
 * The F2 error body in every reactive application on the classpath, before Spring Boot's own: a refusal code
 * on every error, and no message but a refusal's. An application that declares its own error handler or error
 * attributes keeps them.
 */
@AutoConfiguration(before = [ErrorWebFluxAutoConfiguration::class])
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@EnableConfigurationProperties(WebProperties::class)
class F2ExceptionWebFluxAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ErrorAttributes::class)
    fun f2ErrorAttributes(): F2ErrorAttributes = F2ErrorAttributes()

    @Bean
    @ConditionalOnMissingBean(ErrorWebExceptionHandler::class)
    @Order(F2ErrorWebExceptionHandler.ORDER)
    fun f2ErrorWebExceptionHandler(
        errorAttributes: ErrorAttributes,
        applicationContext: ApplicationContext,
        webProperties: WebProperties,
        serverCodecConfigurer: ServerCodecConfigurer,
        viewResolvers: ObjectProvider<ViewResolver>,
    ): F2ErrorWebExceptionHandler = F2ErrorWebExceptionHandler(
        errorAttributes, applicationContext, webProperties, serverCodecConfigurer, viewResolvers,
    )
}
