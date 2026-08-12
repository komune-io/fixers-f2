package io.komune.f2.spring.boot.auth.security

import io.komune.f2.spring.boot.auth.ROLE_PREFIX
import io.komune.f2.spring.boot.auth.config.F2TrustedIssuersConfig
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

class TrustedIssuerJwtAuthenticationManagerResolver(
    val trustedIssuersConfig: F2TrustedIssuersConfig
): ReactiveAuthenticationManagerResolver<String> {

    private val authenticationManagers: MutableMap<String, Mono<ReactiveAuthenticationManager>> = ConcurrentHashMap()

    private val issuerBaseUri = trustedIssuersConfig.issuerBaseUri.trimEnd('/')

    override fun resolve(issuer: String): Mono<ReactiveAuthenticationManager> {
        if (!isTrustedIssuer(issuer)) return Mono.empty()
        return this.authenticationManagers.computeIfAbsent(issuer) {
            buildAuthenticationManager(issuer).subscribeOn(Schedulers.boundedElastic())
                .cache(
                    /* ttlForValue = */ { Duration.ofMillis(Long.MAX_VALUE) },
                    /* ttlForError = */ { Duration.ZERO },
                    /* ttlForEmpty = */ { Duration.ZERO }
                )
        }
    }

    /**
     * An issuer is trusted only when it is the configured `f2.tenant.issuer-base-uri` itself, or one
     * of its descendants: the base URI must be followed by a path separator, so a sibling merely
     * sharing the prefix (`.../realms/tenant-1-other` for base `.../realms/tenant-1`) is not trusted.
     *
     * Trailing slashes are ignored on both sides, so `.../realms` and `.../realms/` describe the same
     * trust set. An empty base URI trusts nothing (the tenant filter chain is not active in that case
     * anyway, see `AUTHENTICATION_REQUIRED_EXPRESSION`).
     */
    fun isTrustedIssuer(issuer: String): Boolean {
        if (issuerBaseUri.isEmpty()) return false
        val candidate = issuer.trimEnd('/')
        return candidate == issuerBaseUri || candidate.startsWith("$issuerBaseUri/")
    }

    private fun buildAuthenticationManager(issuer: String) = Mono.fromCallable<ReactiveAuthenticationManager> {
        JwtReactiveAuthenticationManager(
            ReactiveJwtDecoders.fromIssuerLocation(issuer)
        ).apply {
            setJwtAuthenticationConverter(jwtAuthenticationConverter())
        }
    }

    fun jwtAuthenticationConverter(): ReactiveJwtAuthenticationConverter {
        return ReactiveJwtAuthenticationConverter().apply {
            setJwtGrantedAuthoritiesConverter(::jwtAuthoritiesConverter)
        }
    }

    fun jwtAuthoritiesConverter(jwt: Jwt): Flux<GrantedAuthority> {
        val realmAccess = jwt.claims["realm_access"] as? Map<*, *>
        val roles = (realmAccess?.get("roles") as? List<*>).orEmpty().filterIsInstance<String>()
        return roles.map { role ->
            SimpleGrantedAuthority("$ROLE_PREFIX$role")
        }.let { Flux.fromIterable(it) }
    }
}
