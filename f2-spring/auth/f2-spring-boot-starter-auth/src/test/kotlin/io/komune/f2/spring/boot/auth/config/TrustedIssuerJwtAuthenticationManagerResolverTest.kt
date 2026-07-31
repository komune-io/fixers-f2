package io.komune.f2.spring.boot.auth.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter

class TrustedIssuerJwtAuthenticationManagerResolverTest {

    companion object {
        const val TRUSTED_ISSUER = "http://localhost:8080/auth/realms/master"
    }

    private val resolver = TrustedIssuerJwtAuthenticationManagerResolver(
        isTrustedIssuer = { issuer -> issuer == TRUSTED_ISSUER },
        reactiveJwtAuthenticationConverter = ReactiveJwtAuthenticationConverter()
    )

    @Test
    fun `resolve should return empty mono for untrusted issuer`() {
        val manager = resolver.resolve("http://evil.example.com/realms/master").block()
        assertThat(manager).isNull()
    }

    @Test
    fun `resolve should return a manager mono for trusted issuer`() {
        assertThat(resolver.resolve(TRUSTED_ISSUER)).isNotNull
    }

    @Test
    fun `resolve should cache the manager mono per issuer`() {
        val first = resolver.resolve(TRUSTED_ISSUER)
        val second = resolver.resolve(TRUSTED_ISSUER)
        assertThat(second).isSameAs(first)
    }
}
