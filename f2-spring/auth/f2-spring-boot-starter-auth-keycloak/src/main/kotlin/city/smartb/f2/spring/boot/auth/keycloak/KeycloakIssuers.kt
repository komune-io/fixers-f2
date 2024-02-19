package io.komune.f2.spring.boot.auth.keycloak

import io.komune.f2.spring.boot.auth.config.TrustedIssuerProperties

class KeycloakIssuers(
    name: String,
    authUrl: String,
    realm: String,
    val web: I2KeycloakProperties?
): TrustedIssuerProperties(
    name = name,
    authUrl = authUrl,
    realm = realm
)

class I2KeycloakProperties(
    val clientId: String
)
