package io.komune.f2.spring.boot.auth.keycloak

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "f2")
data class F2KeycloakConfig (
    val issuers: List<KeycloakIssuers> = emptyList()
) {
    val config: Map<String, KeycloakConfig>
        get() = issuers.associate { issuer ->
            issuer.name to KeycloakConfig(
                realm = issuer.realm,
                authServerUrl = issuer.authUrl,
                resource = issuer.web?.clientId
            )
        }
}
