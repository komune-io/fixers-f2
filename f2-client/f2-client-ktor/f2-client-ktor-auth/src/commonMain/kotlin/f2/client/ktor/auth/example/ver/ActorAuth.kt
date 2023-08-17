package f2.client.ktor.auth.example.ver

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.core.use
import kotlinx.serialization.Serializable

object ActorAuth {
    suspend fun getActor(authUrl: String, name: String, clientId: String, clientSecret: String): Actor {
        val token = getTokens(authUrl, clientId, clientSecret)
        return Actor(name, token)
    }
    suspend fun getTokens(authUrl: String, clientId: String, clientSecret: String): AccessToken {
        val url = "${authUrl}/protocol/openid-connect/token"
        return HttpClient {

        }.use { client ->
            client.post(url) {
                contentType(ContentType.Application.FormUrlEncoded)
                setBody("grant_type=client_credentials&client_id=${clientId}&client_secret=${clientSecret}")
            }.body()
        }
    }
}

@Serializable
data class AccessToken(
    val access_token: String,
    val expires_in: Int,
    val refresh_expires_in: Int,
    val token_type: String,
    val scope: String,
)

open class Actor(
    val name: String,
    val accessToken: AccessToken
)