package f2.client.ktor.http

import io.ktor.client.engine.HttpClientEngineConfig
import kotlinx.serialization.json.Json

/**
 * Provides a default instance of [HttpClientBuilder].
 *
 * @return A default instance of [HttpClientBuilder].
 */
@JsExport
actual fun httpClientBuilderDefault() = HttpClientBuilder()

/**
 * Provides a generic instance of [HttpClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to [F2DefaultJson].
 * @param config Additional configuration for the HTTP client. Defaults to null.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
@JsExport
actual fun httpClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
) = HttpClientBuilder(json)

/**
 * Provides a generic instance of [HttpClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to [F2DefaultJson].
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 * @return An instance of [HttpClientBuilder] with the specified configuration.
 */
fun httpClientBuilder(
	json: Json? = F2DefaultJson,
	config: F2ClientConfigLambda<HttpClientEngineConfig>? = {  }
) = HttpClientBuilder(json)
