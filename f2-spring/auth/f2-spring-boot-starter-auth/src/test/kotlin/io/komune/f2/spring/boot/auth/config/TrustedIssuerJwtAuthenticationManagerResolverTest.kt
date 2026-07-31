package io.komune.f2.spring.boot.auth.config

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter

class TrustedIssuerJwtAuthenticationManagerResolverTest {

    companion object {
        private const val ISSUER_PATH = "/auth/realms/master"

        private lateinit var server: HttpServer
        private lateinit var trustedIssuer: String

        @JvmStatic
        @BeforeAll
        fun startOidcProviderStub() {
            server = HttpServer.create(InetSocketAddress(0), 0)
            trustedIssuer = "http://localhost:${server.address.port}$ISSUER_PATH"
            server.createContext("$ISSUER_PATH/.well-known/openid-configuration") { exchange ->
                val body = """
                    {
                      "issuer": "$trustedIssuer",
                      "jwks_uri": "$trustedIssuer/protocol/openid-connect/certs"
                    }
                """.trimIndent().toByteArray()
                exchange.responseHeaders.add("Content-Type", "application/json")
                exchange.sendResponseHeaders(200, body.size.toLong())
                exchange.responseBody.use { it.write(body) }
            }
            server.createContext("$ISSUER_PATH/protocol/openid-connect/certs") { exchange ->
                val body = """{"keys":[]}""".toByteArray()
                exchange.responseHeaders.add("Content-Type", "application/json")
                exchange.sendResponseHeaders(200, body.size.toLong())
                exchange.responseBody.use { it.write(body) }
            }
            server.start()
        }

        @JvmStatic
        @AfterAll
        fun stopOidcProviderStub() {
            server.stop(0)
        }
    }

    private val resolver = TrustedIssuerJwtAuthenticationManagerResolver(
        isTrustedIssuer = { issuer -> issuer == trustedIssuer },
        reactiveJwtAuthenticationConverter = ReactiveJwtAuthenticationConverter()
    )

    @Test
    fun `resolve should return empty mono for untrusted issuer`() {
        val manager = resolver.resolve("http://evil.example.com/realms/master").block()
        assertThat(manager).isNull()
    }

    @Test
    fun `resolve should return a manager mono for trusted issuer`() {
        val manager = resolver.resolve(trustedIssuer).block()
        assertThat(manager).isNotNull
    }

    @Test
    fun `resolve should cache the manager mono per issuer`() {
        val first = resolver.resolve(trustedIssuer)
        val second = resolver.resolve(trustedIssuer)
        assertThat(second).isSameAs(first)
    }
}
