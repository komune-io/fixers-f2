package f2.spring.openapi

import org.springdoc.core.properties.SpringDocConfigProperties
import org.springdoc.core.providers.CloudFunctionProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(CloudFunctionProvider::class)
class F2OpenApiAutoConfiguration {

    @Bean
    fun f2CloudFunctionProvider(
        springDocConfigProperties: SpringDocConfigProperties,
        @Value("\${spring.cloud.function.web.path:}") prefix: String,
    ): CloudFunctionProvider {
        return F2CloudFunctionProvider(springDocConfigProperties, prefix)
    }
}
