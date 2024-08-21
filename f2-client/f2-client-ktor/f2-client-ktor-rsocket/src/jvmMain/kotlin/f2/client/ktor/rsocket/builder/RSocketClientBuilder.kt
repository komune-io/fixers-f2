package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Builder class for creating instances of [RSocketClientBuilder].
 *
 * @constructor Creates an instance of [RSocketClientBuilder].
 * @param json The JSON configuration to use. Defaults to `null`.
 * @param config Additional configuration for the RSocket client. Defaults to an empty lambda.
 */
actual class RSocketClientBuilder(
	private val json: Json? = null,
	private val config: F2ClientConfigLambda<CIOEngineConfig>? = {}
) {
	actual fun build(): HttpClient = HttpClient(CIO) {
		applyRSocket()
		applyConfig(json, config)
	}
}

/**
 * Creates a new instance of [RSocketClientBuilder] with default values.
 *
 * @return An instance of [RSocketClientBuilder].
 */
actual fun rsocketClientBuilderDefault(): RSocketClientBuilder {
	return RSocketClientBuilder()
}

/**
 * Creates a new instance of [RSocketClientBuilder] with the specified configuration.
 *
 * @param json The JSON configuration to use.
 * @param config Additional configuration for the RSocket client.
 * @return An instance of [RSocketClientBuilder].
 */
actual fun rsocketClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
): RSocketClientBuilder {
	return RSocketClientBuilder(json, config)
}

