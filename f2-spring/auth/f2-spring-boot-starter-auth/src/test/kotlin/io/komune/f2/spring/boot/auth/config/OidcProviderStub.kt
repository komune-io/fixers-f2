package io.komune.f2.spring.boot.auth.config

import com.nimbusds.jose.JOSEObjectType
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.time.Instant
import java.util.Date
import java.util.UUID

/**
 * In-process OpenID provider stub: serves an openid-configuration document and a JWKS per realm,
 * and mints JWTs signed with the matching realm key.
 *
 * Everything is local (loopback HTTP + locally generated RSA keys), so no Keycloak instance and no
 * network egress is needed to exercise the resource-server filter chain end to end.
 */
class OidcProviderStub(realms: List<String>) {

    private val server: HttpServer = HttpServer.create(InetSocketAddress(0), 0)
    private val keys: Map<String, RSAKey> = realms.associateWith { realm ->
        RSAKeyGenerator(RSA_KEY_SIZE)
            .keyID("$realm-key")
            .generate()
    }

    val authUrl: String get() = "http://localhost:${server.address.port}/auth"

    init {
        realms.forEach { realm ->
            val issuer = issuer(realm)
            server.createContext("/auth/realms/$realm/.well-known/openid-configuration") { exchange ->
                exchange.respondJson(
                    """
                    {
                      "issuer": "$issuer",
                      "jwks_uri": "$issuer/protocol/openid-connect/certs"
                    }
                    """.trimIndent()
                )
            }
            server.createContext("/auth/realms/$realm/protocol/openid-connect/certs") { exchange ->
                exchange.respondJson(JWKSet(keys.getValue(realm).toPublicJWK()).toString())
            }
        }
        server.start()
    }

    fun issuer(realm: String) = "$authUrl/realms/$realm"

    fun stop() = server.stop(0)

    /**
     * @param realm the realm whose signing key is used
     * @param issuer the `iss` claim, defaults to the realm issuer. Override it to simulate a token
     * claiming to come from another issuer while being signed by [realm]'s key.
     */
    @Suppress("LongParameterList")
    fun mintToken(
        realm: String,
        roles: List<String> = emptyList(),
        issuer: String = issuer(realm),
        subject: String = "user-1",
        claims: Map<String, Any> = emptyMap(),
        expiresAt: Instant = Instant.now().plusSeconds(EXPIRY_SECONDS)
    ): String {
        val key = keys.getValue(realm)
        val claimsSet = JWTClaimsSet.Builder()
            .issuer(issuer)
            .subject(subject)
            .jwtID(UUID.randomUUID().toString())
            .issueTime(Date.from(Instant.now().minusSeconds(EXPIRY_SECONDS)))
            .expirationTime(Date.from(expiresAt))
            .claim("realm_access", mapOf("roles" to roles))
            .apply { claims.forEach { (name, value) -> claim(name, value) } }
            .build()

        val header = JWSHeader.Builder(JWSAlgorithm.RS256)
            .keyID(key.keyID)
            .type(JOSEObjectType.JWT)
            .build()

        return SignedJWT(header, claimsSet)
            .apply { sign(RSASSASigner(key)) }
            .serialize()
    }

    private fun HttpExchange.respondJson(body: String) {
        val bytes = body.toByteArray()
        responseHeaders.add("Content-Type", "application/json")
        sendResponseHeaders(200, bytes.size.toLong())
        responseBody.use { it.write(bytes) }
    }

    companion object {
        private const val RSA_KEY_SIZE = 2048
        private const val EXPIRY_SECONDS = 3600L
    }
}
