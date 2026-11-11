package f2.spring.exception.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.webflux.autoconfigure.error.ErrorWebFluxAutoConfiguration
import org.springframework.boot.webflux.error.ErrorAttributes
import org.springframework.context.annotation.Bean

/**
 * The F2 error body in every reactive application on the classpath, before Spring Boot's own: a refusal code
 * on every error, and no message but a refusal's. Spring Boot's error handler answers it, at the status the body
 * names. An application that declares its own error attributes keeps them.
 */
@AutoConfiguration(before = [ErrorWebFluxAutoConfiguration::class])
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
class F2ExceptionWebFluxAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ErrorAttributes::class)
    fun f2ErrorAttributes(): F2ErrorAttributes = F2ErrorAttributes()
}
