package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.js.Js
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.json.Json

/**
 * Builder class for creating instances of [HttpF2Client].
 *
 * @constructor Creates an instance of [HttpClientBuilder].
 */
@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
	private val json: Json? = DefaultJson,
	private val config: F2ClientConfigLambda<HttpClientEngineConfig>? = {}
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
			applyConfig(json, config)
		}
	}

	actual companion object

}
