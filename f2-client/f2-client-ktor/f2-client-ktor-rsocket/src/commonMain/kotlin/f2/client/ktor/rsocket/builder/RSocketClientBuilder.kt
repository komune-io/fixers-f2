package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

/**
 * Builder class for creating instances of [HttpClient] for RSocket.
 *
 * @constructor Creates an instance of [RSocketClientBuilder].
 */
expect class RSocketClientBuilder {
    /**
     * Builds an [HttpClient] for RSocket communication.
     *
     * @return An instance of [HttpClient].
     */
    fun build(): HttpClient
}

/**
 * Provides a default instance of [RSocketClientBuilder].
 *
 * @return A default instance of [RSocketClientBuilder].
 */
expect fun rsocketClientBuilderDefault(): RSocketClientBuilder

/**
 * Provides a generic instance of [RSocketClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to null.
 * @param config Additional configuration for the RSocket client. Defaults to null.
 * @return An instance of [RSocketClientBuilder] with the specified configuration.
 */
expect fun rsocketClientBuilderGenerics(
    json: Json? = null,
    config: F2ClientConfigLambda<*>? = null
): RSocketClientBuilder
