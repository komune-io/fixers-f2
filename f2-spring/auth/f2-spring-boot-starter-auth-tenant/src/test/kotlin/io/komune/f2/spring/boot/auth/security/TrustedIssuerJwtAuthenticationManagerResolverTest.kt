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

        /** Base URI of issuers that are never dereferenced: only the trust decision is asserted. */
        private const val AUTH_BASE_URI = "https://auth.example.org/auth"

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
    fun `resolve should return empty mono for a sibling issuer merely sharing the base uri prefix`() {
        val singleTenantResolver = resolverWithBaseUri(trustedIssuer)

        assertThat(singleTenantResolver.resolve("$trustedIssuer-other").block()).isNull()
        assertThat(singleTenantResolver.resolve(trustedIssuer).block()).isNotNull
    }

    @Test
    fun `a tenant base uri should only trust that tenant and its sub paths`() {
        val singleTenantResolver = resolverWithBaseUri("$AUTH_BASE_URI/realms/tenant-1")

        assertThat(singleTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-1")).isTrue()
        assertThat(singleTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-1/")).isTrue()
        assertThat(singleTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-1/sub")).isTrue()

        assertThat(singleTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-1-other")).isFalse()
        assertThat(singleTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-10")).isFalse()
        assertThat(singleTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-2")).isFalse()
    }

    @Test
    fun `a trailing slash on the base uri should not change the trust set`() {
        val withSlash = resolverWithBaseUri("$AUTH_BASE_URI/realms/tenant-1/")
        val withoutSlash = resolverWithBaseUri("$AUTH_BASE_URI/realms/tenant-1")

        listOf(
            "$AUTH_BASE_URI/realms/tenant-1",
            "$AUTH_BASE_URI/realms/tenant-1/",
            "$AUTH_BASE_URI/realms/tenant-1-other",
            "$AUTH_BASE_URI/realms/tenant-2"
        ).forEach { issuer ->
            assertThat(withSlash.isTrustedIssuer(issuer))
                .describedAs(issuer)
                .isEqualTo(withoutSlash.isTrustedIssuer(issuer))
        }
    }

    @Test
    fun `a realms base uri should trust every realm under it but nothing beside it`() {
        val multiTenantResolver = resolverWithBaseUri("$AUTH_BASE_URI/realms")

        assertThat(multiTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-1")).isTrue()
        assertThat(multiTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms/tenant-2")).isTrue()

        assertThat(multiTenantResolver.isTrustedIssuer("$AUTH_BASE_URI/realms-other/tenant-1")).isFalse()
    }

    @Test
    fun `a host level base uri should not trust a look-alike host`() {
        val hostResolver = resolverWithBaseUri("https://auth.example.org")

        assertThat(hostResolver.isTrustedIssuer("https://auth.example.org/auth/realms/tenant-1")).isTrue()
        assertThat(hostResolver.isTrustedIssuer("https://auth.example.org")).isTrue()

        assertThat(hostResolver.isTrustedIssuer("https://auth.example.org.evil.tld/auth/realms/tenant-1")).isFalse()
        assertThat(hostResolver.isTrustedIssuer("https://auth.example.org.evil.tld")).isFalse()
    }

    @Test
    fun `an empty base uri should trust nothing`() {
        val emptyResolver = resolverWithBaseUri("")

        assertThat(emptyResolver.isTrustedIssuer("https://auth.example.org/auth/realms/tenant-1")).isFalse()
        assertThat(emptyResolver.resolve("https://auth.example.org/auth/realms/tenant-1").block()).isNull()
    }

    private fun resolverWithBaseUri(baseUri: String) = TrustedIssuerJwtAuthenticationManagerResolver(
        trustedIssuersConfig = F2TrustedIssuersConfig(issuerBaseUri = baseUri)
    )

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
