package io.komune.f2.spring.boot.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "f2")
data class F2TrustedIssuersConfig (
    private val issuers: List<TrustedIssuerProperties> = emptyList(),
) {

    val trustedIssuers: List<String>
        get() = issuers.map { it.issuer }
}
