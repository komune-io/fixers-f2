package f2.client.ktor.rsocket.builder

import f2.client.ktor.rsocket.RSocketClient
import f2.client.ktor.rsocket.RSocketF2Client


expect fun rSocketF2ClientBuilderDefault(): RSocketF2ClientBuilder

expect class RSocketF2ClientBuilder {

    suspend fun build(
        url: String,
        secure: Boolean,
    ): RSocketF2Client

    suspend fun RSocketClientBuilder.getClient(
        baseUrl: String,
        bearerJwt: String?,
    ): RSocketClient
}
