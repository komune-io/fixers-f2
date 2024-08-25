package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import io.ktor.client.engine.HttpClientEngineConfig

/**
 * Provides a default instance of [HttpClientBuilder].
 *
 * @return A default instance of [HttpClientBuilder].
 */
@JsExport
actual fun httpClientBuilderDefault() = HttpClientBuilder()

/**
 * Provides a generic instance of [HttpClientBuilder].
 *
 * @param config Additional configuration for the HTTP client. Defaults to null.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
@JsExport
actual fun httpClientBuilderGenerics(
	config: F2ClientConfigLambda<*>?
) = HttpClientBuilder(config)

/**
 * Provides a generic instance of [HttpClientBuilder].
 *
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
fun httpClientBuilder(
	config: F2ClientConfigLambda<HttpClientEngineConfig>? = {  }
) = HttpClientBuilder(config)
