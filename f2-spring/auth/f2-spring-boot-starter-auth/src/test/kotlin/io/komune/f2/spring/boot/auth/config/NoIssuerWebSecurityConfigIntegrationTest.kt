package io.komune.f2.spring.boot.auth.config

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * When no trusted issuer is configured, [WebSecurityConfig.dummyAuthenticationProvider] takes over
 * and every exchange must be permitted (development / no-auth mode).
 */
@SpringBootTest(
    classes = [AuthTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class NoIssuerWebSecurityConfigIntegrationTest {

    @LocalServerPort
    private var port: Int = 0

    private val webTestClient: WebTestClient by lazy {
        WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
    }

    @Test
    fun `secured path should be reachable without token when no issuer is configured`() {
        webTestClient.get().uri("/securedFunction").exchange()
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("secured")
    }

    @Test
    fun `RolesAllowed path should be reachable without token when no issuer is configured`() {
        webTestClient.get().uri("/adminFunction").exchange()
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("admin")
    }
}
