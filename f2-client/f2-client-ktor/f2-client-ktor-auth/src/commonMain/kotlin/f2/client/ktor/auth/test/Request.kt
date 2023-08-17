package f2.client.ktor.auth.test

import f2.client.ktor.auth.impl.model.AuthException
import f2.client.ktor.auth.impl.model.ErrorInfo
import f2.client.ktor.auth.impl.model.TokenInfo
import f2.client.ktor.auth.impl.model.UserInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.formUrlEncode
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


interface OauthMethod {
//    @Throws(AuthException::class)
//    fun authorization()
    @Throws(AuthException::class)
    fun token(): TokenInfo
    @Throws(AuthException::class)
    fun refresh(): TokenInfo
    @Throws(AuthException::class)
    fun userInfo(): UserInfo
}
object Request {

    val clientId = "CLIENT_ID"

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    object Google {

        fun authorizationRequest() {

            val authorizationUrlQuery = parameters {
                append("client_id", clientId)
                append("scope", "https://www.googleapis.com/auth/userinfo.profile")
                append("response_type", "code")
                append("redirect_uri", "http://127.0.0.1:8080")
                append("access_type", "offline")
            }.formUrlEncode()
        }

        fun exchangeRequest() =  GlobalScope.launch {
            val tokenInfo: TokenInfo = client.submitForm(
                url = "https://accounts.google.com/o/oauth2/token",
                formParameters = parameters {
                    append("grant_type", "authorization_code")
                    append("code", authorizationCode)
                    append("client_id", System.getenv("GOOGLE_CLIENT_ID"))
                    append("client_secret", System.getenv("GOOGLE_CLIENT_SECRET"))
                    append("redirect_uri", "http://127.0.0.1:8080")
                }
            ).body()
            bearerTokenStorage.add(BearerTokens(tokenInfo.accessToken, tokenInfo.refreshToken!!))
        }

        fun refreshToken() {
            val refreshTokenInfo: TokenInfo = client.submitForm(
                url = "https://accounts.google.com/o/oauth2/token",
                formParameters = parameters {
                    append("grant_type", "refresh_token")
                    append("client_id", System.getenv("GOOGLE_CLIENT_ID"))
                    append("refresh_token", oldTokens?.refreshToken ?: "")
                }
            ) { markAsRefreshTokenRequest() }.body()
            bearerTokenStorage.add(BearerTokens(refreshTokenInfo.accessToken, oldTokens?.refreshToken!!))
            bearerTokenStorage.last()
        }

        fun userInfo() {
            val response: HttpResponse = client.get("https://www.googleapis.com/oauth2/v2/userinfo")
            try {
                val userInfo: UserInfo = response.body()
                println("Hello, ${userInfo.name}!")
            } catch (e: Exception) {
                val errorInfo: ErrorInfo = response.body()
                println(errorInfo.error.message)
            }
        }
    }

    class Im() {
        fun client_credentials(authUrl: String, clientId: String, clientSecret: String) = GlobalScope.launch {
//            "${properties.auth.url}/realms/${properties.auth.realm}/protocol/openid-connect/token"
            val url = "${authUrl}/protocol/openid-connect/token"
            val token: TokenInfo = client.post(url) {
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(
                    "grant_type=client_credentials&client_id=${clientId}&client_secret=${clientSecret}"
                )
            }.body()
        }
    }
}