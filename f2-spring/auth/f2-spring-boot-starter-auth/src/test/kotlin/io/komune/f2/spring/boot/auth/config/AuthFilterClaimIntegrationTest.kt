package io.komune.f2.spring.boot.auth.config

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * `f2.filter.*` entries are claim/value pairs every authenticated token must match to pass the
 * mandatory-authentication rule of [WebSecurityConfig].
 */
@SpringBootTest(
    classes = [AuthTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class AuthFilterClaimIntegrationTest {

    companion object {
        private const val REALM = "master"
        private val oidc = OidcProviderStub(listOf(REALM))

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("f2.issuers[0].name") { "first" }
            registry.add("f2.issuers[0].auth-url") { oidc.authUrl }
            registry.add("f2.issuers[0].realm") { REALM }
            registry.add("f2.filter.azp") { "trusted-client" }
        }

        @JvmStatic
        @AfterAll
        fun stopOidcProviderStub() = oidc.stop()
    }

    @LocalServerPort
    private var port: Int = 0

    private val webTestClient: WebTestClient by lazy {
        WebTestClient.bindToServer().baseUrl("http://localhost:$port").build()
    }

    private fun getSecured(token: String) = webTestClient.get()
        .uri("/securedFunction")
        .headers { it.setBearerAuth(token) }
        .exchange()

    @Test
    fun `token matching the configured claim filter should be accepted`() {
        val token = oidc.mintToken(REALM, claims = mapOf("azp" to "trusted-client"))
        getSecured(token).expectStatus().isOk
    }

    @Test
    fun `token with a mismatching claim should be rejected with 403`() {
        val token = oidc.mintToken(REALM, claims = mapOf("azp" to "other-client"))
        getSecured(token).expectStatus().isForbidden
    }

    @Test
    fun `token missing the filtered claim should be rejected with 403`() {
        val token = oidc.mintToken(REALM)
        getSecured(token).expectStatus().isForbidden
    }
}
