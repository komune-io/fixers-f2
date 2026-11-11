package io.komune.f2.spring.boot.auth.config

import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.mock.http.server.reactive.MockServerHttpRequest
import org.springframework.mock.web.server.MockServerWebExchange
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.WebFilterChainProxy
import reactor.core.publisher.Mono
import tools.jackson.databind.json.JsonMapper

class WebSecurityConfigTest {

    @Configuration
    class SecuredBeansConfig {
        @Bean("securedFunction")
        @RolesAllowed("admin")
        fun securedFunction(): () -> String = { "secured" }

        @Bean("openFunction")
        @PermitAll
        fun openFunction(): () -> String = { "open" }
    }

    class TestServerHttpSecurity : ServerHttpSecurity() {
        fun withApplicationContext(context: ApplicationContext): TestServerHttpSecurity = apply {
            setApplicationContext(context)
        }
    }

    @Test
    fun `authFilter should return an empty map by default`() {
        withConfig { config, _ ->
            assertThat(config.authFilter()).isEmpty()
        }
    }

    @Test
    fun `dummyAuthenticationProvider should build a permit all filter chain`() {
        withConfig { config, http ->
            val chain = config.dummyAuthenticationProvider(http)

            val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/anything"))
            WebFilterChainProxy(chain).filter(exchange) { _ -> Mono.empty() }.block()

            assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.UNAUTHORIZED)
            assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.FORBIDDEN)
        }
    }

    @Test
    fun `oauthAuthenticationProvider should reject unauthenticated requests`() {
        withConfig { config, http ->
            val chain = config.oauthAuthenticationProvider(http)

            val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/securedFunction"))
            WebFilterChainProxy(chain).filter(exchange) { _ -> Mono.empty() }.block()

            assertThat(exchange.response.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
        }
    }

    @Test
    fun `oauthAuthenticationProvider should permit beans annotated with PermitAll`() {
        withConfig { config, http ->
            val chain = config.oauthAuthenticationProvider(http)

            val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/openFunction"))
            WebFilterChainProxy(chain).filter(exchange) { _ -> Mono.empty() }.block()

            assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.UNAUTHORIZED)
            assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.FORBIDDEN)
        }
    }

    private fun withConfig(block: (WebSecurityConfig, TestServerHttpSecurity) -> Unit) {
        AnnotationConfigApplicationContext(SecuredBeansConfig::class.java).use { beansContext ->
            val config = WebSecurityConfig(
                contextPath = "",
                applicationContext = beansContext,
                f2TrustedIssuersResolver = F2TrustedIssuersConfig(
                    issuerBaseUri = "http://localhost:8080/auth/realms/"
                ),
                jsonMapper = JsonMapper.shared(),
            )
            block(config, TestServerHttpSecurity().withApplicationContext(beansContext))
        }
    }
}
