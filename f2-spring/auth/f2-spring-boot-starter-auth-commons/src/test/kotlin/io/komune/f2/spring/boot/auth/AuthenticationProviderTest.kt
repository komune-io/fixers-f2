package io.komune.f2.spring.boot.auth

import kotlinx.coroutines.reactor.asCoroutineContext
import kotlinx.coroutines.runBlocking
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

class AuthenticationProviderTest {

    companion object {
        const val ISSUER = "http://localhost:8080/auth/realms/tenant-1"
        const val ORGANIZATION_ID = "organization-1"
        const val CLIENT_ID = "client-1"
    }

    private fun jwt(issuer: String = ISSUER): Jwt = Jwt.withTokenValue("token")
        .header("alg", "none")
        .issuer(issuer)
        .claim(ORGANIZATION_ID_CLAIM_NAME, ORGANIZATION_ID)
        .claim(AZP_CLAIM_NAME, CLIENT_ID)
        .build()

    private fun authentication(jwt: Jwt, vararg roles: String) = JwtAuthenticationToken(
        jwt,
        roles.map { role -> SimpleGrantedAuthority("$ROLE_PREFIX$role") }
    )

    private fun <T> withSecurityContext(
        authentication: JwtAuthenticationToken,
        block: suspend () -> T
    ): T = runBlocking {
        val securityContext: SecurityContext = SecurityContextImpl(authentication)
        val reactorContext = Context.of(
            SecurityContext::class.java,
            Mono.just(securityContext)
        )
        withContext(reactorContext.asCoroutineContext()) {
            block()
        }
    }

    @Test
    fun `getSecurityContext should return null without reactor context`() = runBlocking<Unit> {
        assertThat(AuthenticationProvider.getSecurityContext()).isNull()
        assertThat(AuthenticationProvider.getAuthentication()).isNull()
        assertThat(AuthenticationProvider.getPrincipal()).isNull()
        assertThat(AuthenticationProvider.getOrganizationId()).isNull()
        assertThat(AuthenticationProvider.getIssuer()).isNull()
        assertThat(AuthenticationProvider.getClientId()).isNull()
        assertThat(AuthenticationProvider.getTenant()).isNull()
    }

    @Test
    fun `getSecurityContext should return context from coroutine reactor context`() {
        val securityContext = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getSecurityContext()
        }
        assertThat(securityContext).isNotNull
        assertThat(securityContext!!.authentication).isInstanceOf(JwtAuthenticationToken::class.java)
    }

    @Test
    fun `getAuthentication should return the jwt authentication token`() {
        val authentication = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getAuthentication()
        }
        assertThat(authentication).isNotNull
        assertThat(authentication!!.token.tokenValue).isEqualTo("token")
    }

    @Test
    fun `getPrincipal should return the jwt`() {
        val principal = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getPrincipal()
        }
        assertThat(principal).isNotNull
        assertThat(principal!!.tokenValue).isEqualTo("token")
    }

    @Test
    fun `getOrganizationId should return memberOf claim`() {
        val organizationId = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getOrganizationId()
        }
        assertThat(organizationId).isEqualTo(ORGANIZATION_ID)
    }

    @Test
    fun `getIssuer should return issuer claim`() {
        val issuer = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getIssuer()
        }
        assertThat(issuer).isEqualTo(ISSUER)
    }

    @Test
    fun `getClientId should return azp claim`() {
        val clientId = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getClientId()
        }
        assertThat(clientId).isEqualTo(CLIENT_ID)
    }

    @Test
    fun `getTenant should return last segment of issuer`() {
        val tenant = withSecurityContext(authentication(jwt())) {
            AuthenticationProvider.getTenant()
        }
        assertThat(tenant).isEqualTo("tenant-1")
    }

    @Test
    fun `getTenant should return null when issuer ends with slash`() {
        val tenant = withSecurityContext(authentication(jwt(issuer = "http://localhost:8080/"))) {
            AuthenticationProvider.getTenant()
        }
        assertThat(tenant).isNull()
    }

    @Test
    fun `hasRole should return true when authority is present`() {
        val hasRole = withSecurityContext(authentication(jwt(), "admin")) {
            AuthenticationProvider.hasRole("admin")
        }
        assertThat(hasRole).isTrue()
    }

    @Test
    fun `hasRole should return false when authority is missing`() {
        val hasRole = withSecurityContext(authentication(jwt(), "user")) {
            AuthenticationProvider.hasRole("admin")
        }
        assertThat(hasRole).isFalse()
    }

    @Test
    fun `hasRole should return false without authentication`() = runBlocking<Unit> {
        assertThat(AuthenticationProvider.hasRole("admin")).isFalse()
    }
}
