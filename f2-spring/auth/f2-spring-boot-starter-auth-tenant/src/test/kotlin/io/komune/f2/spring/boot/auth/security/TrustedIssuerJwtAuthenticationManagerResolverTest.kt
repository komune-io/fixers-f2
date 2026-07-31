package io.komune.f2.spring.boot.auth.security

import io.komune.f2.spring.boot.auth.ROLE_PREFIX
import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

class TrustedIssuerJwtAuthenticationManagerResolverTest {

    companion object {
        const val ISSUER_BASE_URI = "http://localhost:8080/auth/realms/"
    }

    private val resolver = TrustedIssuerJwtAuthenticationManagerResolver(
        trustedIssuersConfig = F2TrustedIssuersConfig(issuerBaseUri = ISSUER_BASE_URI)
    )

    @Test
    fun `resolve should return empty mono for issuer outside of the base uri`() {
        val manager = resolver.resolve("http://evil.example.com/realms/master").block()
        assertThat(manager).isNull()
    }

    @Test
    fun `resolve should return a manager mono for issuer within the base uri`() {
        assertThat(resolver.resolve("${ISSUER_BASE_URI}tenant-1")).isNotNull
    }

    @Test
    fun `resolve should cache the manager mono per issuer`() {
        val first = resolver.resolve("${ISSUER_BASE_URI}tenant-1")
        val second = resolver.resolve("${ISSUER_BASE_URI}tenant-1")
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
