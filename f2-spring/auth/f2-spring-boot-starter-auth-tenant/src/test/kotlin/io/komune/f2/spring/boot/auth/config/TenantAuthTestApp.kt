package io.komune.f2.spring.boot.auth.config

import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/**
 * Minimal reactive application wiring the real tenant [WebSecurityConfig] filter chain, with one
 * endpoint per authorization rule the config derives from bean annotations.
 */
@SpringBootApplication
class TenantAuthTestApp {

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
        GET("/adminFunction") { ServerResponse.ok().bodyValue("admin") }
        GET("/securedFunction") { ServerResponse.ok().bodyValue("secured") }
    }
}
