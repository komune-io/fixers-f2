package f2.client.ktor.http

import kotlinx.serialization.json.Json

/**
 * Provides a default instance of [HttpClientBuilder].
 *
 * @return A default instance of [HttpClientBuilder].
 */
fun HttpClientBuilder.Companion.default(): HttpClientBuilder = httpClientBuilderDefault()

/**
 * Provides a generic instance of [HttpClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to [F2DefaultJson].
 * @param config Additional configuration for the HTTP client. Defaults to null.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
fun HttpClientBuilder.Companion.generics(
    json: Json?,
    config: F2ClientConfigLambda<*>?
): HttpClientBuilder = httpClientBuilderGenerics(json, config)
