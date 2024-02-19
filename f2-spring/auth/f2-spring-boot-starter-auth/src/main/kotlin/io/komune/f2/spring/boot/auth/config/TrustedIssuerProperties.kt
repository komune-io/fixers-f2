package io.komune.f2.spring.boot.auth.config

open class TrustedIssuerProperties(
    open val name: String,
    open val authUrl: String,
    open val realm: String,
    open val issuer: String = "${authUrl.removeSuffix("/")}/realms/${realm.removePrefix("/")}"
)
