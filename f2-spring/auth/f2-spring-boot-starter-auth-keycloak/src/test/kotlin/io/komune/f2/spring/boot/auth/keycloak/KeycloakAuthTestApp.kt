package io.komune.f2.spring.boot.auth.keycloak

import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import io.komune.f2.spring.boot.auth.config.WebSecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Boots the Keycloak starter (`KeycloakConfigEndpoint`, `KeycloakConfigResolver`, `F2KeycloakConfig`)
 * behind the real [WebSecurityConfig] filter chain and the real F2 WebFlux function routes, so the
 * `keycloak` bean is served at `/keycloak` and its `@PermitAll` is actually exercised by Spring Security.
 */
@SpringBootApplication
class KeycloakAuthTestApp {

    @Configuration
    @EnableConfigurationProperties(F2TrustedIssuersConfig::class)
    class TestWebSecurityConfig : WebSecurityConfig()

    @Bean("securedFunction")
    fun securedFunction(): () -> String = { "secured" }
}
