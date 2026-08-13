package io.komune.f2.spring.boot.auth.config

import io.komune.f2.spring.boot.auth.security.TrustedIssuerJwtAuthenticationManagerResolver
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * End-to-end check of [TrustedIssuerJwtAuthenticationManagerResolver]'s issuer trust boundary
 * (fixed in #140, tracked by #139): an issuer is trusted only when it equals
 * `f2.tenant.issuer-base-uri` or continues with a `/` after it. A sibling realm that merely shares
 * the base as a string prefix (`.../realms/tenant-1-other` for base `.../realms/tenant-1`) must be
 * rejected.
 */
@SpringBootTest(
    classes = [TenantAuthTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class TenantIssuerPrefixMatchingTest {

    companion object {
        const val TENANT = "tenant-1"
        const val SIBLING_TENANT = "tenant-1-other"

        private val oidc = OidcProviderStub(listOf(TENANT, SIBLING_TENANT))

        @JvmStatic
        @DynamicPropertySource
        fun issuerBaseUri(registry: DynamicPropertyRegistry) {
            // no trailing separator on purpose: the resolver must still enforce a segment boundary
            registry.add("f2.tenant.issuer-base-uri") { "${oidc.authUrl}/realms/$TENANT" }
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

    private fun getAdmin(token: String) = webTestClient.get()
        .uri("/adminFunction")
        .headers { it.setBearerAuth(token) }
        .exchange()

    @Test
    fun `token of the configured tenant should be accepted`() {
        getAdmin(oidc.mintToken(TENANT, roles = listOf("admin"))).expectStatus().isOk
    }

    @Test
    fun `token of a sibling issuer sharing the base uri prefix should be rejected with 401`() {
        // `tenant-1-other` shares the configured base as a string prefix but is a different realm:
        // since #140 the resolver requires a path-segment boundary, so it must not be trusted
        getAdmin(oidc.mintToken(SIBLING_TENANT, roles = listOf("admin"))).expectStatus().isUnauthorized
    }

    @Test
    fun `token of an unrelated issuer should still be rejected with 401`() {
        val token = oidc.mintToken(
            realm = TENANT,
            roles = listOf("admin"),
            issuer = "https://evil.example.com/realms/tenant-1"
        )
        getAdmin(token).expectStatus().isUnauthorized
    }
}
