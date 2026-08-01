package io.komune.f2.spring.boot.auth.keycloak

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class KeycloakConfigResolverTest {

    private val f2KeycloakConfig = F2KeycloakConfig(
        issuers = listOf(
            KeycloakIssuers(
                name = "first",
                authUrl = "http://localhost:8080/auth",
                realm = "master",
                web = I2KeycloakProperties(clientId = "web-client")
            ),
            KeycloakIssuers(
                name = "second",
                authUrl = "http://localhost:8081/auth",
                realm = "tenant",
                web = null
            )
        )
    )
    private val resolver = KeycloakConfigResolver(f2KeycloakConfig)

    @Test
    fun `getConfig should map issuers by name`() {
        val config = f2KeycloakConfig.getConfig()

        assertThat(config).containsOnlyKeys("first", "second")
        assertThat(config["first"]).isEqualTo(
            KeycloakConfig(realm = "master", authServerUrl = "http://localhost:8080/auth", resource = "web-client")
        )
        assertThat(config["second"]).isEqualTo(
            KeycloakConfig(realm = "tenant", authServerUrl = "http://localhost:8081/auth", resource = null)
        )
    }

    @Test
    suspend fun `getKeycloakConfig should return first config for null name`() {
        val config = resolver.getKeycloakConfig(null)
        assertThat(config.realm).isEqualTo("master")
    }

    @Test
    suspend fun `getKeycloakConfig should return first config for blank name`() {
        val config = resolver.getKeycloakConfig("")
        assertThat(config.realm).isEqualTo("master")
    }

    @Test
    suspend fun `getKeycloakConfig should return config matching the name`() {
        val config = resolver.getKeycloakConfig("second")
        assertThat(config.realm).isEqualTo("tenant")
    }

    @Test
    fun `getKeycloakConfig should fail for unknown name`() {
        assertThatThrownBy {
            runBlocking { resolver.getKeycloakConfig("unknown") }
        }.isInstanceOf(NullPointerException::class.java)
            .hasMessageContaining("unknown")
    }

    @Test
    fun `keycloak endpoint function should return the resolved config`() {
        val endpoint = KeycloakConfigEndpoint(resolver)

        val config = endpoint.keycloak()("first")

        assertThat(config).isEqualTo(
            KeycloakConfig(realm = "master", authServerUrl = "http://localhost:8080/auth", resource = "web-client")
        )
    }
}
