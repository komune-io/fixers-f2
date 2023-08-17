package f2.client.ktor.auth.impl

import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.http.*
import io.ktor.util.AttributeKey

val F2AuthPlugin = createClientPlugin("F2AuthPlugin", ::AuthPluginConfig) {
    val realm = pluginConfig.auth.toAuthRealm()
    client.config {
        install(Auth) {
            when(realm) {
                is AuthRealmClientSecret -> {
                    if(!realm.isPublic) {
                            basicAuth(realm)
                        } else {

                        }
                    }

                is AuthRealmPassword -> TODO()
            }

            bearer {
                loadTokens {
                    BearerTokens("abc123", "xyz111")
                }
            }
        }
    }

    on(Send) { request ->
        val originalCall = proceed(request)
        originalCall.response.run { // this: HttpResponse
            if (status == HttpStatusCode.Unauthorized && headers["WWW-Authenticate"]!!.contains("Bearer")) {
                request.headers.append("Authorization", "Bearer $token")
                proceed(request)
            } else {
                originalCall
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

val ResponseTimePlugin = createClientPlugin("ResponseTimePlugin") {
    val onCallTimeKey = AttributeKey<Long>("onCallTimeKey")
    client.config {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = "jetbrains", password = "foobar")
                }
            }
            bearer {
                loadTokens {
                    BearerTokens("abc123", "xyz111")
                }
            }
        }
    }
    on(SendingRequest) { request, content ->
        val onCallTime = System.currentTimeMillis()
        request.attributes.put(onCallTimeKey, onCallTime)
    }

    onResponse { response ->
        val onCallTime = response.call.attributes[onCallTimeKey]
        val onCallReceiveTime = System.currentTimeMillis()
        println("Read response delay (ms): ${onCallReceiveTime - onCallTime}")
    }
}

