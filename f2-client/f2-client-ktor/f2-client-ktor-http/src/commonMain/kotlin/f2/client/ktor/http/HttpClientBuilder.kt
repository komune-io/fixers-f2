package f2.client.ktor.http

import kotlinx.serialization.json.Json


/**
 * Builder class for creating instances of [HttpF2Client].
 *
 * @constructor Creates an instance of [HttpClientBuilder].
 */
expect class HttpClientBuilder {
    companion object

    /**
     * Builds an [HttpF2Client] with the specified base URL.
     *
     * @param urlBase The base URL for the HTTP client.
     * @return An instance of [HttpF2Client].
     */
    fun build(urlBase: String): HttpF2Client
}

/**
 * Provides a default instance of [HttpClientBuilder].
 *
 * @return A default instance of [HttpClientBuilder].
 */
expect fun httpClientBuilderDefault(): HttpClientBuilder

/**
 * Provides a generic instance of [HttpClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to [F2DefaultJson].
 * @param config Additional configuration for the HTTP client. Defaults to null.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
expect fun httpClientBuilderGenerics(
    json: Json? = F2DefaultJson,
    config: F2ClientConfigLambda<*>? = null
): HttpClientBuilder

