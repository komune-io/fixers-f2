package f2.client.ktor.http.plugin

import f2.client.ktor.http.plugin.model.AuthRealm
import f2.client.ktor.http.plugin.model.AuthRealmClientSecret
import f2.client.ktor.http.plugin.model.AuthRealmPassword
import f2.client.ktor.http.plugin.model.TokenInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.parametersOf
import io.ktor.util.AttributeKey

class F2Auth {
    private lateinit var authPlugin: Auth

    lateinit var getAuth: suspend () -> AuthRealm

    companion object Plugin: HttpClientPlugin<F2Auth, F2Auth> {
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
                    val refreshTokenInfo: TokenInfo = client.post {
                        val authRealm = getAuth()
                        url("${authRealm.serverUrl}/realms/${authRealm.realmId}/protocol/openid-connect/token")
                        val parameters = if (oldTokens == null) {
                            when (authRealm) {
                                is AuthRealmClientSecret -> mapOf(
                                    "grant_type" to "client_credentials",
                                    "client_id" to authRealm.clientId,
                                    "client_secret" to authRealm.clientSecret,
                                )
                                is AuthRealmPassword -> mapOf(
                                    "grant_type" to "password",
                                    "client_id" to authRealm.clientId,
                                    "scope" to "openid",
                                    "username" to authRealm.username,
                                    "password" to authRealm.password
                                )
                            }
                        } else {
                            mapOf(
                                "grant_type" to "refresh_token",
                                "client_id" to authRealm.clientId,
                                "refresh_token" to (oldTokens?.refreshToken ?: "")
                            )
                        }.map { (key, value) -> key to listOf(value) }
                            .toTypedArray()
                        setBody(FormDataContent(parametersOf(*parameters)))
                        markAsRefreshTokenRequest()
                    }.body()
                    lastBearerTokens = BearerTokens(refreshTokenInfo.accessToken, refreshTokenInfo.refreshToken ?: "")
                    lastBearerTokens
                }
            }
        }
    }
}

private fun Auth.basicAuth(realm: AuthRealmClientSecret) {
    basic {
        credentials {
            BasicAuthCredentials(username = realm.clientId, password = realm.clientSecret)
        }
    }
}
