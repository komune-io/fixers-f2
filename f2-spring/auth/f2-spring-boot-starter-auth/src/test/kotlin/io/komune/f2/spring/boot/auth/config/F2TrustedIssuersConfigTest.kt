package io.komune.f2.spring.boot.auth.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class F2TrustedIssuersConfigTest {

    @Test
    fun `should build issuer from authUrl and realm`() {
        val properties = TrustedIssuerProperties(
            name = "keycloak",
            authUrl = "http://localhost:8080/auth",
            realm = "master"
        )
        assertThat(properties.issuer).isEqualTo("http://localhost:8080/auth/realms/master")
    }

    @Test
    fun `should build issuer trimming authUrl trailing slash and realm leading slash`() {
        val properties = TrustedIssuerProperties(
            name = "keycloak",
            authUrl = "http://localhost:8080/auth/",
            realm = "/master"
        )
        assertThat(properties.issuer).isEqualTo("http://localhost:8080/auth/realms/master")
    }

    @Test
    fun `getTrustedIssuers should return the issuers of all configured properties`() {
        val config = F2TrustedIssuersConfig(
            issuers = listOf(
                TrustedIssuerProperties(name = "one", authUrl = "http://auth-1", realm = "realm-1"),
                TrustedIssuerProperties(name = "two", authUrl = "http://auth-2", realm = "realm-2"),
            )
        )
        assertThat(config.getTrustedIssuers()).containsExactly(
            "http://auth-1/realms/realm-1",
            "http://auth-2/realms/realm-2"
        )
    }

    @Test
    fun `getTrustedIssuers should return empty list by default`() {
        assertThat(F2TrustedIssuersConfig().getTrustedIssuers()).isEmpty()
    }
}
