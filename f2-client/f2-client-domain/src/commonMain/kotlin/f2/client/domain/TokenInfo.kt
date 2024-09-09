package f2.client.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("refresh_expires_in") val refreshExpiresIn: Int? = null,
    @SerialName("token_type") val tokenType: String,
    @SerialName("scope") val scope: String?,
    @SerialName("id_token") val idToken: String? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
)
