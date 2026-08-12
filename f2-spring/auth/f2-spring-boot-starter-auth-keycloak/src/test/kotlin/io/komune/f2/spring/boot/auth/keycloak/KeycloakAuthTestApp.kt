package io.komune.f2.spring.boot.auth.keycloak

import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import io.komune.f2.spring.boot.auth.config.WebSecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/**
 * Boots the Keycloak starter (`KeycloakConfigEndpoint`, `KeycloakConfigResolver`, `F2KeycloakConfig`)
 * behind the real [WebSecurityConfig] filter chain, so the `@PermitAll` declared on the `keycloak`
 * bean is actually exercised by Spring Security.
 */
@SpringBootApplication
class KeycloakAuthTestApp {

    @Configuration
    @EnableConfigurationProperties(F2TrustedIssuersConfig::class)
    class TestWebSecurityConfig : WebSecurityConfig()

    @Bean("securedFunction")
    fun securedFunction(): () -> String = { "secured" }

    @Bean
    fun routes(): RouterFunction<ServerResponse> = router {
        GET("/keycloak") { ServerResponse.ok().bodyValue("keycloak-config") }
        GET("/securedFunction") { ServerResponse.ok().bodyValue("secured") }
    }
}
