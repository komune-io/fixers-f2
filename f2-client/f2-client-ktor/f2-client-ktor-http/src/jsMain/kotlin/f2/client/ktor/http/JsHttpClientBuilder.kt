package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.engine.js.JsClientEngineConfig

/**
 * [HttpClientBuilder] on the Ktor Js engine.
 *
 * @param config Additional configuration for the HTTP client.
 */
internal class JsHttpClientBuilder(
	private val config: F2ClientConfigLambda<JsClientEngineConfig>?
) : HttpClientBuilder {

	override fun build(urlBase: String): HttpF2Client = HttpF2Client(
		urlBase = urlBase,
		httpClient = HttpClient(Js) { applyConfig(config) }
	)
}
