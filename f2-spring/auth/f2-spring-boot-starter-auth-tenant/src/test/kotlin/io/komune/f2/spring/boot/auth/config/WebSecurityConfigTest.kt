package io.komune.f2.spring.boot.auth.config

import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
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

class WebSecurityConfigTest {

    @Configuration
    open class SecuredBeansConfig {
        @Bean("securedFunction")
        @RolesAllowed("admin")
        open fun securedFunction(): () -> String = { "secured" }

        @Bean("openFunction")
        @PermitAll
        open fun openFunction(): () -> String = { "open" }
    }

    class TestServerHttpSecurity : ServerHttpSecurity() {
        fun withApplicationContext(context: ApplicationContext): TestServerHttpSecurity = apply {
            setApplicationContext(context)
        }
    }

    private lateinit var beansContext: AnnotationConfigApplicationContext
    private lateinit var config: WebSecurityConfig

    @BeforeEach
    fun setUp() {
        beansContext = AnnotationConfigApplicationContext(SecuredBeansConfig::class.java)
        config = WebSecurityConfig().apply {
            contextPath = ""
            applicationContext = beansContext
            f2TrustedIssuersResolver = F2TrustedIssuersConfig(
                issuerBaseUri = "http://localhost:8080/auth/realms/"
            )
        }
    }

    @AfterEach
    fun tearDown() {
        beansContext.close()
    }

    private fun http() = TestServerHttpSecurity().withApplicationContext(beansContext)

    @Test
    fun `authFilter should return an empty map by default`() {
        assertThat(config.authFilter()).isEmpty()
    }

    @Test
    fun `dummyAuthenticationProvider should build a permit all filter chain`() {
        val chain = config.dummyAuthenticationProvider(http())

        val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/anything"))
        WebFilterChainProxy(chain).filter(exchange) { _ -> Mono.empty() }.block()

        assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.UNAUTHORIZED)
        assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.FORBIDDEN)
    }

    @Test
    fun `oauthAuthenticationProvider should reject unauthenticated requests`() {
        val chain = config.oauthAuthenticationProvider(http())

        val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/securedFunction"))
        WebFilterChainProxy(chain).filter(exchange) { _ -> Mono.empty() }.block()

        assertThat(exchange.response.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun `oauthAuthenticationProvider should permit beans annotated with PermitAll`() {
        val chain = config.oauthAuthenticationProvider(http())

        val exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/openFunction"))
        WebFilterChainProxy(chain).filter(exchange) { _ -> Mono.empty() }.block()

        assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.UNAUTHORIZED)
        assertThat(exchange.response.statusCode).isNotEqualTo(HttpStatus.FORBIDDEN)
    }
}
