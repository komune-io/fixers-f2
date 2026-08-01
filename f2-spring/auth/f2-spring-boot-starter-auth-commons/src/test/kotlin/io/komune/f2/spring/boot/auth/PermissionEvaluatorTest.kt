package io.komune.f2.spring.boot.auth

import kotlinx.coroutines.reactor.asCoroutineContext
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import reactor.core.publisher.Mono
import reactor.util.context.Context

class PermissionEvaluatorTest {

    private val permissionEvaluator = PermissionEvaluator()

    private fun jwt(): Jwt = Jwt.withTokenValue("token")
        .header("alg", "none")
        .claim(ORGANIZATION_ID_CLAIM_NAME, "organization-1")
        .build()

    private suspend fun <T> withAuthentication(
        vararg roles: String,
        block: suspend () -> T
    ): T {
        val authentication = JwtAuthenticationToken(
            jwt(),
            roles.map { role -> SimpleGrantedAuthority("$ROLE_PREFIX$role") }
        )
        val securityContext: SecurityContext = SecurityContextImpl(authentication)
        val reactorContext = Context.of(
            SecurityContext::class.java,
            Mono.just(securityContext)
        )
        return withContext(reactorContext.asCoroutineContext()) {
            block()
        }
    }

    @Test
    suspend fun `isSuperAdmin should return true with super_admin role`() {
        val isSuperAdmin = withAuthentication(SUPER_ADMIN_ROLE) {
            permissionEvaluator.isSuperAdmin()
        }
        assertThat(isSuperAdmin).isTrue()
    }

    @Test
    suspend fun `isSuperAdmin should return false without super_admin role`() {
        val isSuperAdmin = withAuthentication("user") {
            permissionEvaluator.isSuperAdmin()
        }
        assertThat(isSuperAdmin).isFalse()
    }

    @Test
    suspend fun `isSuperAdmin should return false without authentication`() {
        assertThat(permissionEvaluator.isSuperAdmin()).isFalse()
    }

    @Test
    suspend fun `checkOrganizationId should return false for null organizationId`() {
        assertThat(permissionEvaluator.checkOrganizationId(null)).isFalse()
    }

    @Test
    suspend fun `checkOrganizationId should return true when it matches the authenticated organization`() {
        val checked = withAuthentication("user") {
            permissionEvaluator.checkOrganizationId("organization-1")
        }
        assertThat(checked).isTrue()
    }

    @Test
    suspend fun `checkOrganizationId should return false when it does not match`() {
        val checked = withAuthentication("user") {
            permissionEvaluator.checkOrganizationId("organization-2")
        }
        assertThat(checked).isFalse()
    }

    @Test
    suspend fun `checkOrganizationId should return false without authentication`() {
        assertThat(permissionEvaluator.checkOrganizationId("organization-1")).isFalse()
    }
}
