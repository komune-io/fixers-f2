package io.komune.f2.spring.boot.auth.config

import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import tools.jackson.databind.json.JsonMapper

/**
 * Minimal reactive application wiring the real [WebSecurityConfig] filter chain.
 *
 * It exposes one endpoint per authorization rule the config derives from bean annotations:
 * - `openFunction`   -> `@PermitAll`
 * - `adminFunction`  -> `@RolesAllowed("admin")`
 * - `securedFunction` -> no annotation, falls through to the mandatory-authentication rule
 */
@SpringBootApplication
class AuthTestApp {

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

    @Bean("openFunction")
    @PermitAll
    fun openFunction(): () -> String = { "open" }

    @Bean("adminFunction")
    @RolesAllowed("admin")
    fun adminFunction(): () -> String = { "admin" }

    @Bean("securedFunction")
    fun securedFunction(): () -> String = { "secured" }

    @Bean
    fun routes(): RouterFunction<ServerResponse> = router {
        GET("/openFunction") { ServerResponse.ok().bodyValue("open") }
        PUT("/openFunction") { ServerResponse.ok().bodyValue("open") }
        DELETE("/openFunction") { ServerResponse.ok().bodyValue("open") }
        GET("/adminFunction") { ServerResponse.ok().bodyValue("admin") }
        GET("/securedFunction") { ServerResponse.ok().bodyValue("secured") }
    }
}
