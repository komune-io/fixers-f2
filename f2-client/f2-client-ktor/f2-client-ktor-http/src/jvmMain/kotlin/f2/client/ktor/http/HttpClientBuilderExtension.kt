@file:JvmName("HttpClientBuilderUtils")
package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import io.ktor.client.engine.cio.CIOEngineConfig



/**
 * Provides a default instance of [HttpClientBuilder].
 *
 * @return A default instance of [HttpClientBuilder].
 */
actual fun httpClientBuilderDefault(): HttpClientBuilder = HttpClientBuilder()

/**
 * Provides a generic instance of [HttpClientBuilder].
 *
 * @param config Additional configuration for the HTTP client. Defaults to null.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
actual fun httpClientBuilderGenerics(
    config: F2ClientConfigLambda<*>?
): HttpClientBuilder = HttpClientBuilder(config)


/**
 * Provides a generic instance of [HttpClientBuilder].
 *
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
fun httpClientBuilder(
    config: F2ClientConfigLambda<CIOEngineConfig>? = {  }
) = HttpClientBuilder(config)

/**
 * Provides an instance of [HttpClientBuilder] using the builder function.
 *
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
fun HttpClientBuilder.Companion.builder(
    config: F2ClientConfigLambda<CIOEngineConfig>? = { }
) = HttpClientBuilder(config)
