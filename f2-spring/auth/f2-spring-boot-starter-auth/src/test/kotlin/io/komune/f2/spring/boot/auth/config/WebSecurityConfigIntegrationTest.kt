package io.komune.f2.spring.boot.auth.config

import java.time.Instant
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * End-to-end coverage of the security filter chain built by [WebSecurityConfig] when authentication
 * is required (at least one trusted issuer configured). Tokens are minted locally by
 * [OidcProviderStub], nothing leaves the loopback interface.
 */
@SpringBootTest(
    classes = [AuthTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class WebSecurityConfigIntegrationTest {

    companion object {
        const val FIRST_REALM = "master"
        const val SECOND_REALM = "tenant"

        private val oidc = OidcProviderStub(listOf(FIRST_REALM, SECOND_REALM))

        @JvmStatic
        @DynamicPropertySource
        fun trustedIssuers(registry: DynamicPropertyRegistry) {
            registry.add("f2.issuers[0].name") { "first" }
            registry.add("f2.issuers[0].auth-url") { oidc.authUrl }
            registry.add("f2.issuers[0].realm") { FIRST_REALM }
            registry.add("f2.issuers[1].name") { "second" }
            registry.add("f2.issuers[1].auth-url") { oidc.authUrl }
            registry.add("f2.issuers[1].realm") { SECOND_REALM }
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

    private fun get(path: String, token: String? = null) = webTestClient.get()
        .uri(path)
        .apply { token?.let { headers { headers -> headers.setBearerAuth(it) } } }
        .exchange()

    @Test
    fun `unauthenticated request on a secured path should be rejected with 401`() {
        get("/securedFunction").expectStatus().isUnauthorized
    }

    @Test
    fun `unauthenticated request on a RolesAllowed path should be rejected with 401`() {
        get("/adminFunction").expectStatus().isUnauthorized
    }

    @Test
    fun `PermitAll path should be reachable without any token`() {
        get("/openFunction")
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("open")
    }

    @Test
    fun `authenticated request with an insufficient role should be rejected with 403`() {
        val token = oidc.mintToken(FIRST_REALM, roles = listOf("user"))
        get("/adminFunction", token).expectStatus().isForbidden
    }

    @Test
    fun `authenticated request with the required role should be accepted`() {
        val token = oidc.mintToken(FIRST_REALM, roles = listOf("admin"))
        get("/adminFunction", token)
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("admin")
    }

    @Test
    fun `authenticated request without any role should reach a path requiring only authentication`() {
        val token = oidc.mintToken(FIRST_REALM)
        get("/securedFunction", token)
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("secured")
    }

    @Test
    fun `token issued by the second trusted issuer should be accepted`() {
        val token = oidc.mintToken(SECOND_REALM, roles = listOf("admin"))
        get("/adminFunction", token)
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("admin")
    }

    @Test
    fun `token issued by an untrusted issuer should be rejected with 401`() {
        val token = oidc.mintToken(
            realm = FIRST_REALM,
            roles = listOf("admin"),
            issuer = "https://evil.example.com/realms/master"
        )
        get("/adminFunction", token).expectStatus().isUnauthorized
    }

    @Test
    fun `token signed by another issuer key should be rejected with 401`() {
        // claims to come from the second issuer but is signed with the first issuer key:
        // the JWKS of the resolved issuer must be the one used for verification
        val token = oidc.mintToken(
            realm = FIRST_REALM,
            roles = listOf("admin"),
            issuer = oidc.issuer(SECOND_REALM)
        )
        get("/adminFunction", token).expectStatus().isUnauthorized
    }

    @Test
    fun `expired token should be rejected with 401`() {
        val token = oidc.mintToken(
            realm = FIRST_REALM,
            roles = listOf("admin"),
            expiresAt = Instant.now().minusSeconds(600)
        )
        get("/adminFunction", token).expectStatus().isUnauthorized
    }

    @Test
    fun `malformed bearer token should be rejected with 401`() {
        get("/securedFunction", "not-a-jwt").expectStatus().isUnauthorized
    }
}
