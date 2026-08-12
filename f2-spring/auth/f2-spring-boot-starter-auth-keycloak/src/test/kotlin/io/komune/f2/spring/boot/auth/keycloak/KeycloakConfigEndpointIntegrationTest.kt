package io.komune.f2.spring.boot.auth.keycloak

import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * Covers the Keycloak starter inside a running application: `f2.issuers[*]` binding into both
 * [F2KeycloakConfig] and [F2TrustedIssuersConfig], and the fact that the `keycloak` configuration
 * endpoint is reachable anonymously (it is annotated `@PermitAll`) while the rest of the
 * application requires a token.
 */
@SpringBootTest(
    classes = [KeycloakAuthTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "f2.issuers[0].name=first",
        "f2.issuers[0].auth-url=http://localhost:8080/auth",
        "f2.issuers[0].realm=master",
        "f2.issuers[0].web.client-id=web-client",
        "f2.issuers[1].name=second",
        "f2.issuers[1].auth-url=http://localhost:8081/auth",
        "f2.issuers[1].realm=tenant"
    ]
)
class KeycloakConfigEndpointIntegrationTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var keycloakConfig: F2KeycloakConfig

    @Autowired
    private lateinit var trustedIssuersConfig: F2TrustedIssuersConfig

    @Autowired
    private lateinit var keycloakEndpoint: KeycloakConfigEndpoint

    private val webTestClient: WebTestClient by lazy {
        WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
    }

    @Test
    fun `f2 issuers should bind to KeycloakIssuers including the optional web client id`() {
        assertThat(keycloakConfig.issuers).hasSize(2)
        assertThat(keycloakConfig.getConfig()).containsOnlyKeys("first", "second")
        assertThat(keycloakConfig.getConfig()["first"]).isEqualTo(
            KeycloakConfig(realm = "master", authServerUrl = "http://localhost:8080/auth", resource = "web-client")
        )
        assertThat(keycloakConfig.getConfig()["second"]).isEqualTo(
            KeycloakConfig(realm = "tenant", authServerUrl = "http://localhost:8081/auth", resource = null)
        )
    }

    @Test
    fun `the same f2 issuers should bind to the trusted issuers of the security config`() {
        assertThat(trustedIssuersConfig.getTrustedIssuers()).containsExactly(
            "http://localhost:8080/auth/realms/master",
            "http://localhost:8081/auth/realms/tenant"
        )
    }

    @Test
    fun `keycloak endpoint bean should resolve the config of the requested issuer`() {
        assertThat(keycloakEndpoint.keycloak()("second")).isEqualTo(
            KeycloakConfig(realm = "tenant", authServerUrl = "http://localhost:8081/auth", resource = null)
        )
    }

    @Test
    fun `keycloak config endpoint should be reachable without a token`() {
        webTestClient.get().uri("/keycloak").exchange()
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("keycloak-config")
    }

    @Test
    fun `other endpoints should still require authentication`() {
        webTestClient.get().uri("/securedFunction").exchange()
            .expectStatus().isUnauthorized
    }
}
