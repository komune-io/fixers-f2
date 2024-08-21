package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import kotlinx.serialization.json.Json

/**
 * Builder class for creating instances of [HttpClient] for RSocket.
 *
 * @constructor Creates an instance of [RSocketClientBuilder].
 */
actual class RSocketClientBuilder(
	private val json: Json? = null,
	private val config: F2ClientConfigLambda<*>? = {}
) {
	actual fun build(): HttpClient = HttpClient(Js) {
		applyRSocket()
		applyConfig(json, config)
	}
}

/**
 * Provides a default instance of [RSocketClientBuilder].
 *
 * @return A default instance of [RSocketClientBuilder].
 */
actual fun rsocketClientBuilderDefault(): RSocketClientBuilder {
	return RSocketClientBuilder()
}

/**
 * Provides a generic instance of [RSocketClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to null.
 * @param config Additional configuration for the RSocket client. Defaults to null.
 * @return An instance of [RSocketClientBuilder] with the specified configuration.
 */
actual fun rsocketClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
): RSocketClientBuilder {
	return RSocketClientBuilder(json, config)
}
