package io.komune.f2.spring.boot.auth.config

import io.komune.f2.spring.boot.auth.ROLE_PREFIX
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
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
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

    class TestWebSecurityConfig : WebSecurityConfig()

    class TestServerHttpSecurity : ServerHttpSecurity() {
        fun withApplicationContext(context: ApplicationContext): TestServerHttpSecurity = apply {
            setApplicationContext(context)
        }
    }

    private lateinit var beansContext: AnnotationConfigApplicationContext
    private lateinit var config: TestWebSecurityConfig

    @BeforeEach
    fun setUp() {
        beansContext = AnnotationConfigApplicationContext(SecuredBeansConfig::class.java)
        config = TestWebSecurityConfig().apply {
            contextPath = ""
            applicationContext = beansContext
            f2TrustedIssuersResolver = F2TrustedIssuersConfig(
                issuers = listOf(
                    TrustedIssuerProperties(name = "keycloak", authUrl = "http://localhost:8080/auth", realm = "master")
                )
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
    fun `trustedIssuers should expose configured issuers`() {
        assertThat(config.trustedIssuers())
            .containsExactly("http://localhost:8080/auth/realms/master")
    }

    @Test
    fun `isTrustedIssuer should accept only configured issuers`() {
        assertThat(config.isTrustedIssuer("http://localhost:8080/auth/realms/master")).isTrue()
        assertThat(config.isTrustedIssuer("http://evil.example.com/realms/master")).isFalse()
    }

    @Test
    fun `jwtAuthoritiesConverter should map realm_access roles to prefixed authorities`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("realm_access", mapOf("roles" to listOf("admin", "user")))
            .build()

        val authorities = config.jwtAuthoritiesConverter(jwt).collectList().block()

        assertThat(authorities!!.map { it.authority })
            .containsExactly("${ROLE_PREFIX}admin", "${ROLE_PREFIX}user")
    }

    @Test
    fun `jwtAuthoritiesConverter should return no authority without realm_access claim`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("sub", "user-1")
            .build()

        val authorities = config.jwtAuthoritiesConverter(jwt).collectList().block()

        assertThat(authorities).isEmpty()
    }

    @Test
    fun `jwtAuthenticationConverter should convert jwt to authentication token with authorities`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("realm_access", mapOf("roles" to listOf("admin")))
            .build()

        val authentication = config.jwtAuthenticationConverter().convert(jwt)!!.block()

        assertThat(authentication).isInstanceOf(JwtAuthenticationToken::class.java)
        assertThat(authentication!!.authorities.map { it.authority })
            .containsExactly("${ROLE_PREFIX}admin")
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
