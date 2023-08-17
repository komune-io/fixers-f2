package f2.client.ktor.auth.impl

import f2.client.ktor.auth.impl.model.TokenInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.api.createClientPlugin
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

    lateinit var auth: AuthRealm

    companion object Plugin: HttpClientPlugin<F2Auth, F2Auth> {
        private val bearerTokenStorage = mutableListOf<BearerTokens>()

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
                    bearerTokenStorage.lastOrNull()
                }
                refreshTokens {
                    val refreshTokenInfo: TokenInfo = client.post {
                        val authRealm = auth
                        url("${auth.serverUrl}/realms/${auth.realmId}/protocol/openid-connect/token")
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
                    bearerTokenStorage.add(BearerTokens(refreshTokenInfo.accessToken, refreshTokenInfo.refreshToken ?: ""))
                    bearerTokenStorage.last()
                }
            }
        }
    }
}

val F2AuthPlugin = createClientPlugin("F2AuthPlugin") {
//    val realm = pluginConfig.auth.toAuthRealm()

    client.config {
        install(Auth) {
//            when(realm) {
//                is AuthRealmClientSecret -> {
//                    if(!realm.isPublic) {
//                            basicAuth(realm)
//                        } else {
//
//                        }
//                    }
//
//                is AuthRealmPassword -> TODO()
//            }

            bearer {
                loadTokens {
                    BearerTokens("abc123", "xyz111")
                }
            }
        }
    }

//    on(Send) { request ->
//        val originalCall = proceed(request)
//        originalCall.response.run { // this: HttpResponse
//            if (status == HttpStatusCode.Unauthorized && headers["WWW-Authenticate"]!!.contains("Bearer")) {
//                request.headers.append("Authorization", "Bearer $token")
//                proceed(request)
//            } else {
//                originalCall
//            }
//        }
//    }
}

private fun Auth.basicAuth(realm: AuthRealmClientSecret) {
    basic {
        credentials {
            BasicAuthCredentials(username = realm.clientId, password = realm.clientSecret)
        }
    }
}

//val ResponseTimePlugin = createClientPlugin("ResponseTimePlugin") {
//    val onCallTimeKey = AttributeKey<Long>("onCallTimeKey")
//    client.config {
//        install(Auth) {
//            basic {
//                credentials {
//                    BasicAuthCredentials(username = "jetbrains", password = "foobar")
//                }
//            }
//            bearer {
//                loadTokens {
//                    BearerTokens("abc123", "xyz111")
//                }
//            }
//        }
//    }
//    on(SendingRequest) { request, content ->
//        val onCallTime = System.currentTimeMillis()
//        request.attributes.put(onCallTimeKey, onCallTime)
//    }
//
//    onResponse { response ->
//        val onCallTime = response.call.attributes[onCallTimeKey]
//        val onCallReceiveTime = System.currentTimeMillis()
//        println("Read response delay (ms): ${onCallReceiveTime - onCallTime}")
//    }
//}
