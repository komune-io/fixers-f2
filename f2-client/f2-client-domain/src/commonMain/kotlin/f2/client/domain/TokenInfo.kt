package f2.client.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** The token response of an OAuth2 provider, as `F2Auth` reads it; its `toString` names no token. */
@Serializable
data class TokenInfo(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("refresh_expires_in") val refreshExpiresIn: Int? = null,
    @SerialName("token_type") val tokenType: String,
    @SerialName("scope") val scope: String?,
    @SerialName("id_token") val idToken: String? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
) {
    override fun toString(): String = "TokenInfo(tokenType=$tokenType, expiresIn=$expiresIn, scope=${scope ?: "null"})"
}
