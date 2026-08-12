package io.komune.f2.spring.boot.auth.config

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * End-to-end coverage of the multi-tenant security filter chain: any issuer under
 * `f2.tenant.issuer-base-uri` is trusted, everything else must be rejected. Tokens are minted
 * locally by [OidcProviderStub], nothing leaves the loopback interface.
 */
@SpringBootTest(
    classes = [TenantAuthTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class TenantWebSecurityConfigIntegrationTest {

    companion object {
        const val TENANT_1 = "tenant-1"
        const val TENANT_2 = "tenant-2"

        private val oidc = OidcProviderStub(listOf(TENANT_1, TENANT_2))

        @JvmStatic
        @DynamicPropertySource
        fun issuerBaseUri(registry: DynamicPropertyRegistry) {
            registry.add("f2.tenant.issuer-base-uri") { "${oidc.authUrl}/realms/" }
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
    fun `PermitAll path should be reachable without any token`() {
        get("/openFunction")
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("open")
    }

    @Test
    fun `authenticated request with an insufficient role should be rejected with 403`() {
        val token = oidc.mintToken(TENANT_1, roles = listOf("user"))
        get("/adminFunction", token).expectStatus().isForbidden
    }

    @Test
    fun `token of the first tenant should be accepted`() {
        val token = oidc.mintToken(TENANT_1, roles = listOf("admin"))
        get("/adminFunction", token)
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("admin")
    }

    @Test
    fun `token of the second tenant should be accepted`() {
        val token = oidc.mintToken(TENANT_2, roles = listOf("admin"))
        get("/adminFunction", token)
            .expectStatus().isOk
            .expectBody(String::class.java).isEqualTo("admin")
    }

    @Test
    fun `token issued outside of the tenant base uri should be rejected with 401`() {
        val token = oidc.mintToken(
            realm = TENANT_1,
            roles = listOf("admin"),
            issuer = "https://evil.example.com/realms/tenant-1"
        )
        get("/adminFunction", token).expectStatus().isUnauthorized
    }

    @Test
    fun `token signed by another tenant key should be rejected with 401`() {
        // claims to come from tenant-2 but is signed with the tenant-1 key: each tenant issuer must
        // be verified against its own JWKS
        val token = oidc.mintToken(
            realm = TENANT_1,
            roles = listOf("admin"),
            issuer = oidc.issuer(TENANT_2)
        )
        get("/adminFunction", token).expectStatus().isUnauthorized
    }

    @Test
    fun `malformed bearer token should be rejected with 401`() {
        get("/securedFunction", "not-a-jwt").expectStatus().isUnauthorized
    }
}
