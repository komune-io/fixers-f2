package f2.client.ktor.http.plugin

import com.sun.net.httpserver.HttpServer
import f2.client.domain.AuthRealm
import f2.client.domain.AuthRealmClientSecret
import f2.client.domain.AuthRealmPassword
import f2.dsl.cqrs.exception.F2Exception
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import java.net.InetSocketAddress
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class F2AuthTest {

    companion object {
        private const val REALM_ID = "test-realm"
        private const val TOKEN_PATH = "/realms/$REALM_ID/protocol/openid-connect/token"
    }

    private class TokenServer(vararg responses: String) : AutoCloseable {
        val requestBodies = mutableListOf<String>()
        private val server: HttpServer = HttpServer.create(InetSocketAddress(0), 0)

        init {
            var index = 0
            server.createContext(TOKEN_PATH) { exchange ->
                requestBodies.add(exchange.requestBody.readAllBytes().decodeToString())
                val response = responses[minOf(index, responses.size - 1)].toByteArray()
                index++
                exchange.responseHeaders.add("Content-Type", "application/json")
                exchange.sendResponseHeaders(200, response.size.toLong())
                exchange.responseBody.use { it.write(response) }
            }
            server.start()
        }

        val url: String get() = "http://localhost:${server.address.port}"

        override fun close() = server.stop(0)
    }

    private fun tokenJson(accessToken: String, refreshToken: String? = null): String {
        val refresh = refreshToken?.let { ""","refresh_token":"$it"""" } ?: ""
        return """{"access_token":"$accessToken","expires_in":300,""" +
            """"token_type":"Bearer","scope":"openid"$refresh}"""
    }

    private fun clientSecretRealm(serverUrl: String) = AuthRealmClientSecret(
        serverUrl = serverUrl,
        realmId = REALM_ID,
        clientId = "test-client",
        clientSecret = "test-secret",
    )

    private fun protectedClient(
        realm: AuthRealm,
        acceptedTokens: Set<String>,
        authHeaders: MutableList<String?> = mutableListOf(),
    ): HttpClient = HttpClient(MockEngine { request ->
        val auth = request.headers[HttpHeaders.Authorization]
        authHeaders.add(auth)
        if (auth != null && auth.removePrefix("Bearer ") in acceptedTokens) {
            respond(
                content = """{"ok":true}""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        } else {
            respond(
                content = "Unauthorized",
                status = HttpStatusCode.Unauthorized,
                headers = headersOf(HttpHeaders.WWWAuthenticate, "Bearer")
            )
        }
    }) {
        install(F2Auth) {
            getAuth = { realm }
        }
    }

    @Test
    suspend fun `fetches a token with client credentials and authenticates the request`() {
        TokenServer(tokenJson("token-cs")).use { server ->
            val client = protectedClient(
                realm = clientSecretRealm(server.url),
                acceptedTokens = setOf("token-cs")
            )

            val response = client.get("http://protected/api")

            assertThat(response.status).isEqualTo(HttpStatusCode.OK)
            assertThat(response.bodyAsText()).contains("true")
            assertThat(server.requestBodies.first())
                .contains("grant_type=client_credentials")
                .contains("client_id=test-client")
                .contains("client_secret=test-secret")
            client.close()
        }
    }

    @Test
    suspend fun `refreshes the token when the server rejects the current one`() {
        TokenServer(
            tokenJson("token-r1", refreshToken = "refresh-1"),
            tokenJson("token-r2"),
        ).use { server ->
            val client = protectedClient(
                realm = clientSecretRealm(server.url),
                acceptedTokens = setOf("token-r2")
            )

            val response = client.get("http://protected/api")

            assertThat(response.status).isEqualTo(HttpStatusCode.OK)
            assertThat(server.requestBodies).hasSize(2)
            assertThat(server.requestBodies.last())
                .contains("grant_type=refresh_token")
                .contains("refresh_token=refresh-1")
            client.close()
        }
    }

    @Test
    suspend fun `fetches a token with password grant`() {
        TokenServer(tokenJson("token-pw")).use { server ->
            val realm = AuthRealmPassword(
                serverUrl = server.url,
                realmId = REALM_ID,
                redirectUrl = "",
                clientId = "test-client",
                username = "john",
                password = "doe",
            )
            val client = protectedClient(realm = realm, acceptedTokens = setOf("token-pw"))

            val response = client.get("http://protected/api")

            assertThat(response.status).isEqualTo(HttpStatusCode.OK)
            assertThat(server.requestBodies.first())
                .contains("grant_type=password")
                .contains("username=john")
                .contains("password=doe")
            client.close()
        }
    }

    @Test
    suspend fun `fails with F2Exception when the token response cannot be decoded`() {
        TokenServer("not-a-json").use { server ->
            val client = protectedClient(
                realm = clientSecretRealm(server.url),
                acceptedTokens = setOf("any")
            )

            val thrown = runCatching { client.get("http://protected/api") }.exceptionOrNull()

            assertThat(thrown).isNotNull()
            val f2Exception = generateSequence(thrown) { it.cause }
                .filterIsInstance<F2Exception>()
                .firstOrNull()
            assertThat(f2Exception).isNotNull()
            assertThat(f2Exception!!.error.message).contains("Unable to decode response")
            client.close()
        }
    }
}
