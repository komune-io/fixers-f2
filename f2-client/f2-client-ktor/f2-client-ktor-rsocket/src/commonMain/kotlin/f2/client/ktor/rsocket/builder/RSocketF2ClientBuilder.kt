package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.rsocket.RSocketClient
import f2.client.ktor.rsocket.RSocketF2Client

/**
 * Provides a default instance of [RSocketF2ClientBuilder].
 *
 * @return A default instance of [RSocketF2ClientBuilder].
 */
expect fun rSocketF2ClientBuilderDefault(): RSocketF2ClientBuilder

/**
 * Provides a generic instance of [RSocketF2ClientBuilder].
 *
 * @param config Additional configuration for the RSocket client. Defaults to null.
 * @return An instance of [RSocketF2ClientBuilder] with the specified configuration.
 */
expect fun rSocketF2ClientBuilderGenerics(
    config: F2ClientConfigLambda<*>? = null
): RSocketF2ClientBuilder

/**
 * Builder class for creating instances of [RSocketF2Client] and [RSocketClient].
 */
expect class RSocketF2ClientBuilder {

    /**
     * Builds an [RSocketF2Client] for RSocket communication.
     *
     * @param url The URL to connect to.
     * @param secure Whether to use a secure connection.
     * @return An instance of [RSocketF2Client].
     */
    suspend fun build(
        url: String,
        secure: Boolean,
    ): RSocketF2Client

    /**
     * Builds an [RSocketClient] for RSocket communication.
     *
     * @param baseUrl The base URL to connect to.
     * @param bearerJwt The JWT token for authentication. Defaults to null.
     * @return An instance of [RSocketClient].
     */
    suspend fun rsocketClient(
        baseUrl: String,
        bearerJwt: String?,
    ): RSocketClient
}
