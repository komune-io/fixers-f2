package io.komune.f2.spring.boot.auth.keycloak

import org.springframework.stereotype.Service

@Service
class KeycloakConfigResolver(
    private val f2KeycloakConfig: F2KeycloakConfig
) {
    suspend fun getKeycloakConfig(name: String?): KeycloakConfig {
        if (name.isNullOrBlank()) {
            return f2KeycloakConfig.getConfig().values.first()
        }

        return f2KeycloakConfig.getConfig()[name]
            ?: throw NullPointerException("No config found for this name $name")
    }
}
