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
 * Documents the current behaviour of [TrustedIssuerJwtAuthenticationManagerResolver], which trusts
 * any issuer whose URI *starts with* `f2.tenant.issuer-base-uri` — no path-segment boundary is
 * enforced.
 *
 * With a base URI that does not end with a separator (`.../realms/tenant-1`), a sibling issuer whose
 * name merely shares that prefix (`.../realms/tenant-1-other`) is therefore accepted as trusted.
 * This test asserts the behaviour as it is today so any change is deliberate; see the discussion on
 * issue #123.
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
            // note the missing trailing separator, which is what makes the prefix match too wide
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
    fun `token of a sibling issuer sharing the base uri prefix is currently accepted`() {
        // current behaviour: prefix matching has no segment boundary, so `tenant-1-other` passes the
        // trust check even though only `tenant-1` was configured
        getAdmin(oidc.mintToken(SIBLING_TENANT, roles = listOf("admin"))).expectStatus().isOk
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
