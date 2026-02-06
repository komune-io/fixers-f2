package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.engine.js.JsClientEngineConfig

/**
 * Builder class for creating instances of [HttpF2Client].
 *
 * @constructor Creates an instance of [HttpClientBuilder].
 * @param config Additional configuration for the HTTP client. Defaults to an empty lambda.
 */
@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
	private val config: F2ClientConfigLambda<JsClientEngineConfig>? = {}
) {

	/**
	 * Builds an [HttpF2Client] with the specified base URL.
	 *
	 * @param urlBase The base URL for the HTTP client.
	 * @return An instance of [HttpF2Client].
	 */
	actual fun build(urlBase: String): HttpF2Client {
		return HttpF2Client(
			urlBase = urlBase,
			httpClient = httpClient()
		)
	}

	private fun httpClient(): HttpClient {
		return HttpClient(Js) {
			applyConfig(config)
		}
	}

	actual companion object

}
