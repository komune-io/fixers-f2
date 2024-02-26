package io.komune.f2.spring.boot.auth.keycloak

data class KeycloakConfig(
    val realm: String,
    val authServerUrl: String,
    val resource: String?
)
