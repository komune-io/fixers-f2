package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda

/**
 * Provides a default instance of [HttpClientBuilder].
 *
 * @return A default instance of [HttpClientBuilder].
 */
fun HttpClientBuilder.Companion.default(): HttpClientBuilder = httpClientBuilderDefault()

/**
 * Provides a generic instance of [HttpClientBuilder].
 *
 * @param config Additional configuration for the HTTP client. Defaults to null.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
fun HttpClientBuilder.Companion.generics(
    config: F2ClientConfigLambda<*>?
): HttpClientBuilder = httpClientBuilderGenerics(config)
