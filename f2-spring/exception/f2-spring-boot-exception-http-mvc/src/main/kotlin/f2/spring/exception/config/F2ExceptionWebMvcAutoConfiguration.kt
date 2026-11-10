package f2.spring.exception.config

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration
import org.springframework.boot.webmvc.error.ErrorAttributes
import org.springframework.context.annotation.Bean
import tools.jackson.databind.json.JsonMapper

/**
 * The F2 error body in every servlet application on the classpath, before Spring Boot's own: a refusal code
 * on every error, and no message but a refusal's. An application that declares its own error attributes,
 * F2 exception handler or wrapped-failure resolver keeps them.
 */
@AutoConfiguration(before = [ErrorMvcAutoConfiguration::class])
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
class F2ExceptionWebMvcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ErrorAttributes::class)
    fun f2ErrorAttributes(): F2ErrorAttributes = F2ErrorAttributes()

    @Bean
    @ConditionalOnMissingBean(F2ExceptionHandler::class)
    fun f2ExceptionHandler(): F2ExceptionHandler = F2ExceptionHandler()

    @Bean
    @ConditionalOnMissingBean(F2WrappedFailureResolver::class)
    fun f2WrappedFailureResolver(jsonMapper: ObjectProvider<JsonMapper>): F2WrappedFailureResolver =
        F2WrappedFailureResolver(jsonMapper.getIfAvailable { JsonMapper.shared() })
}
