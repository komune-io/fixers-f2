package io.komune.f2.spring.boot.auth.keycloak

import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import io.komune.f2.spring.boot.auth.config.WebSecurityConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.json.JsonMapper

/**
 * Boots the Keycloak starter (`KeycloakConfigEndpoint`, `KeycloakConfigResolver`, `F2KeycloakConfig`)
 * behind the real [WebSecurityConfig] filter chain and the real F2 WebFlux function routes, so the
 * `keycloak` bean is served at `/keycloak` and its `@PermitAll` is actually exercised by Spring Security.
 */
@SpringBootApplication
class KeycloakAuthTestApp {

    @Configuration
    @EnableConfigurationProperties(F2TrustedIssuersConfig::class)
    class TestWebSecurityConfig(
        @Value("\${spring.cloud.function.web.path:}")
        contextPath: String,
        applicationContext: ApplicationContext,
        f2TrustedIssuersResolver: F2TrustedIssuersConfig,
        jsonMapper: JsonMapper,
    ) : WebSecurityConfig(
        contextPath = contextPath,
        applicationContext = applicationContext,
        f2TrustedIssuersResolver = f2TrustedIssuersResolver,
        jsonMapper = jsonMapper,
    )

    @Bean("securedFunction")
    fun securedFunction(): () -> String = { "secured" }
}
