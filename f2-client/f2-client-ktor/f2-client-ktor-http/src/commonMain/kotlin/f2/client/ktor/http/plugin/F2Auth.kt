package f2.client.ktor.http.plugin

import f2.client.ktor.common.F2DefaultJson
import f2.client.domain.AuthRealm
import f2.client.domain.AuthRealmClientSecret
import f2.client.domain.AuthRealmPassword
import f2.client.domain.TokenInfo
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.asException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.RefreshTokensParams
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.parametersOf
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.serialization.json.Json


class F2Auth(
    protected var json: Json = F2DefaultJson,
    private val debug: Boolean = true
) {

    private lateinit var authPlugin: Auth

    lateinit var getAuth: suspend () -> AuthRealm

    companion object Plugin: HttpClientPlugin<F2Auth, F2Auth> {
        val LOGGER = KtorSimpleLogger("f2.client.ktor.http.plugin.F2Auth")
        private var lastBearerTokens: BearerTokens? = null

        override val key: AttributeKey<F2Auth> = AttributeKey("F2Auth")

        override fun prepare(block: F2Auth.() -> Unit): F2Auth {
            return F2Auth().apply {
                block()
                authPlugin = prepareAuth()
            }
        }

        override fun install(plugin: F2Auth, scope: HttpClient) {
            Auth.install(plugin.authPlugin, scope)
        }

        private fun F2Auth.prepareAuth() = Auth.prepare {
            bearer {
                loadTokens {
                    lastBearerTokens
                }

                refreshTokens {
                    val authRealm = getAuth()
                    val parameters: Map<String, String> = if (oldTokens?.refreshToken.isNullOrBlank()) {
                        generateNewToken(authRealm)
                    } else {
                        refreshToken(authRealm)
                    }
                    val params = parametersOf(parameters.mapValues { (_, value) -> listOf(value) })
                    val refreshTokenInfoRequest = client.post {
                        url("${authRealm.serverUrl}/realms/${authRealm.realmId}/protocol/openid-connect/token")
                        setBody(FormDataContent(params))
                        markAsRefreshTokenRequest()
                    }
                    val tokenInfo = refreshTokenInfoRequest.body<String>()
                    try {
                        val refreshTokenInfo = json.decodeFromString<TokenInfo>(tokenInfo)
                        lastBearerTokens = BearerTokens(
                            refreshTokenInfo.accessToken,
                            refreshTokenInfo.refreshToken ?: ""
                        )
                        lastBearerTokens
                    } catch (e: IllegalArgumentException) {
                        val debugInfo = if (debug) "Invalid argument encountered: $tokenInfo" else ""
                        throw F2Error(
                            message = "Unable to decode response from auth provider. $debugInfo"
                        ).asException(e)
                    } catch (e: Exception) {
                        val debugInfo = if (debug) " Response: $tokenInfo" else ""
                        throw F2Error(
                            message = "Unknown exception occurred.$debugInfo"
                        ).asException(e)
                    }
                }
            }
        }


        private fun RefreshTokensParams.refreshToken(authRealm: AuthRealm): Map<String, String> {
            LOGGER.debug("Refresh Token: grant_type[refresh_token] with client_id[${authRealm.clientId}] " +
                    "and refresh_token=${oldTokens?.refreshToken?.take(n=5)}...}")
            return mapOf(
                "grant_type" to "refresh_token",
                "client_id" to authRealm.clientId,
                "refresh_token" to (oldTokens?.refreshToken ?: "")
            )
        }

        private fun generateNewToken(authRealm: AuthRealm): Map<String, String>  = when (authRealm) {
            is AuthRealmClientSecret -> {
                LOGGER.debug("Generate token: grant_type[client_credentials] " +
                        "with client_id[${authRealm.clientId}] and client_secret=***")
                mapOf(
                    "grant_type" to "client_credentials",
                    "client_id" to authRealm.clientId,
                    "client_secret" to authRealm.clientSecret,
                )
            }

            is AuthRealmPassword -> {
                LOGGER.debug("Generate token: grant_type[password] " +
                        "with client_id ${authRealm.clientId}, username[${authRealm.username}] and password=***")
                mapOf(
                    "grant_type" to "password",
                    "client_id" to authRealm.clientId,
                    "scope" to "openid",
                    "username" to authRealm.username,
                    "password" to authRealm.password
                )
            }
        }
    }
}

//private fun Auth.basicAuth(realm: AuthRealmClientSecret) {
//    basic {
//        credentials {
//            BasicAuthCredentials(username = realm.clientId, password = realm.clientSecret)
//        }
//    }
//}
