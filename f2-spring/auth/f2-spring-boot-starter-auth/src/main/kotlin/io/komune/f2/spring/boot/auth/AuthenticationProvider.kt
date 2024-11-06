package io.komune.f2.spring.boot.auth

import io.komune.f2.spring.boot.auth.config.WebSecurityConfig
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.reactor.ReactorContext
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import reactor.core.publisher.Mono

const val ORGANIZATION_ID_CLAIM_NAME = "memberOf"
const val AZP_CLAIM_NAME = "azp"

object AuthenticationProvider {
    suspend fun getSecurityContext(): SecurityContext? {
        return coroutineContext[ReactorContext.Key]
            ?.context?.get<Mono<SecurityContext>>(SecurityContext::class.java)
            ?.awaitSingleOrNull()
    }

    suspend fun getAuthentication(): JwtAuthenticationToken? {
        return getSecurityContext()?.authentication as JwtAuthenticationToken?
    }

    suspend fun getPrincipal(): Jwt? {
        return getAuthentication()?.principal as Jwt?
    }

    suspend fun getOrganizationId(): String? {
        return getPrincipal()?.getClaim<String>(ORGANIZATION_ID_CLAIM_NAME)
    }

    suspend fun getIssuer(): String {
        return getPrincipal()?.issuer.toString()
    }

    suspend fun getClientId(): String? {
        return getPrincipal()?.getClaim<String>(AZP_CLAIM_NAME)
    }

    suspend fun hasRole(role: String): Boolean {
        return SimpleGrantedAuthority("${WebSecurityConfig.ROLE_PREFIX}$role") in getAuthentication()
            ?.authorities
            .orEmpty()
    }
}
