package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Builder class for creating instances of [HttpF2Client].
 *
 * @constructor Creates an instance of [HttpClientBuilder].
 * @param json The JSON configuration to use. Defaults to [F2DefaultJson].
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 */
actual class HttpClientBuilder(
	private val json: Json? = F2DefaultJson,
	private val config: F2ClientConfigLambda<CIOEngineConfig>? = {}
) {
	/**
	 * Builds an [HttpF2Client] with the specified base URL.
	 *
	 * @param urlBase The base URL for the HTTP client.
	 * @return An instance of [HttpF2Client].
	 */
	actual fun build(urlBase: String): HttpF2Client {
		val httpClient = httpClient()
		return HttpF2Client(
			httpClient = httpClient,
			urlBase = urlBase
		)
	}

	/**
	 * Creates an [HttpClient] instance with the specified configuration.
	 *
	 * @return An instance of [HttpClient].
	 */
	private fun httpClient(): HttpClient {
		return HttpClient(CIO) {
			applyConfig(json, config)
		}
	}

	actual companion object
}
