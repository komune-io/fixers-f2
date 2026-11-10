package io.komune.f2.spring.boot.auth

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
const val ROLE_PREFIX = "ROLE_"

object AuthenticationProvider {
    suspend fun getSecurityContext(): SecurityContext? = coroutineContext[ReactorContext.Key]
        ?.context
        ?.getOrDefault<Mono<SecurityContext>>(SecurityContext::class.java, null)
        ?.awaitSingleOrNull()

    suspend fun getAuthentication(): JwtAuthenticationToken? =
        getSecurityContext()?.authentication as? JwtAuthenticationToken

    suspend fun getPrincipal(): Jwt? = getAuthentication()?.principal as? Jwt

    suspend fun getOrganizationId(): String? = getPrincipal()?.getClaim<String>(ORGANIZATION_ID_CLAIM_NAME)

    suspend fun getIssuer(): String? = getPrincipal()?.issuer?.toString()

    suspend fun getClientId(): String? = getPrincipal()?.getClaim<String>(AZP_CLAIM_NAME)

    suspend fun getTenant(): String? = getIssuer()?.run {
        substringAfterLast(delimiter = "/", missingDelimiterValue = "").ifEmpty { null }
    }

    suspend fun hasRole(role: String): Boolean {
        return SimpleGrantedAuthority("$ROLE_PREFIX$role") in getAuthentication()
            ?.authorities
            .orEmpty()
    }
}
