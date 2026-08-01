package io.komune.f2.spring.boot.auth.security

import com.sun.net.httpserver.HttpServer
import io.komune.f2.spring.boot.auth.ROLE_PREFIX
import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import java.net.InetSocketAddress
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

class TrustedIssuerJwtAuthenticationManagerResolverTest {

    companion object {
        private const val TENANT_PATH = "/auth/realms/tenant-1"

        private lateinit var server: HttpServer
        private lateinit var issuerBaseUri: String
        private lateinit var trustedIssuer: String

        @JvmStatic
        @BeforeAll
        fun startOidcProviderStub() {
            server = HttpServer.create(InetSocketAddress(0), 0)
            issuerBaseUri = "http://localhost:${server.address.port}/auth/realms/"
            trustedIssuer = "http://localhost:${server.address.port}$TENANT_PATH"
            server.createContext("$TENANT_PATH/.well-known/openid-configuration") { exchange ->
                val body = """
                    {
                      "issuer": "$trustedIssuer",
                      "jwks_uri": "$trustedIssuer/protocol/openid-connect/certs"
                    }
                """.trimIndent().toByteArray()
                exchange.responseHeaders.add("Content-Type", "application/json")
                exchange.sendResponseHeaders(200, body.size.toLong())
                exchange.responseBody.use { it.write(body) }
            }
            server.createContext("$TENANT_PATH/protocol/openid-connect/certs") { exchange ->
                val body = """{"keys":[]}""".toByteArray()
                exchange.responseHeaders.add("Content-Type", "application/json")
                exchange.sendResponseHeaders(200, body.size.toLong())
                exchange.responseBody.use { it.write(body) }
            }
            server.start()
        }

        @JvmStatic
        @AfterAll
        fun stopOidcProviderStub() {
            server.stop(0)
        }
    }

    private val resolver = TrustedIssuerJwtAuthenticationManagerResolver(
        trustedIssuersConfig = F2TrustedIssuersConfig(issuerBaseUri = issuerBaseUri)
    )

    @Test
    fun `resolve should return empty mono for issuer outside of the base uri`() {
        val manager = resolver.resolve("http://evil.example.com/realms/master").block()
        assertThat(manager).isNull()
    }

    @Test
    fun `resolve should return a manager mono for issuer within the base uri`() {
        val manager = resolver.resolve(trustedIssuer).block()
        assertThat(manager).isNotNull
    }

    @Test
    fun `resolve should cache the manager mono per issuer`() {
        val first = resolver.resolve(trustedIssuer)
        val second = resolver.resolve(trustedIssuer)
        assertThat(second).isSameAs(first)
    }

    @Test
    fun `jwtAuthoritiesConverter should map realm_access roles to prefixed authorities`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("realm_access", mapOf("roles" to listOf("admin", "user")))
            .build()

        val authorities = resolver.jwtAuthoritiesConverter(jwt).collectList().block()

        assertThat(authorities!!.map { it.authority })
            .containsExactly("${ROLE_PREFIX}admin", "${ROLE_PREFIX}user")
    }

    @Test
    fun `jwtAuthoritiesConverter should return no authority without realm_access claim`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("sub", "user-1")
            .build()

        val authorities = resolver.jwtAuthoritiesConverter(jwt).collectList().block()

        assertThat(authorities).isEmpty()
    }

    @Test
    fun `jwtAuthenticationConverter should convert jwt to authentication token with authorities`() {
        val jwt = Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("realm_access", mapOf("roles" to listOf("admin")))
            .build()

        val authentication = resolver.jwtAuthenticationConverter().convert(jwt)!!.block()

        assertThat(authentication).isInstanceOf(JwtAuthenticationToken::class.java)
        assertThat(authentication!!.authorities.map { it.authority })
            .containsExactly("${ROLE_PREFIX}admin")
    }
}
