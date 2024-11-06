package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.engine.java.JavaHttpConfig

/**
 * Builder class for creating instances of [HttpF2Client].
 *
 * @constructor Creates an instance of [HttpClientBuilder].
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 */
actual class HttpClientBuilder(
	private val config: F2ClientConfigLambda<JavaHttpConfig>? = {}
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
		return HttpClient(Java) {
			applyConfig(config)
		}
	}

	actual companion object
}
