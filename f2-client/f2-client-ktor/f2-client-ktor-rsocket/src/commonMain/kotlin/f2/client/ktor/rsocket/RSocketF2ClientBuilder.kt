package f2.client.ktor.rsocket


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
