package f2.client.ktor.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
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
	private val config: HttpClientConfig<HttpClientEngineConfig>.() -> Unit = {}
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
			json?.let {
				install(ContentNegotiation) {
					json(json)
				}
			}

			config.let { it(this) }
		}
	}

	actual companion object

}
