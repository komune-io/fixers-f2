package f2.client.ktor.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


typealias F2ClientConfigLambda = HttpClientConfig<CIOEngineConfig>.() -> Unit

actual class HttpClientBuilder(
	private val json: Json? = F2DefaultJson,
	private val config: F2ClientConfigLambda? = null
) {
	fun build(
		urlBase: String

	): HttpF2Client {
		val httpCLient = httpClient(json ?: F2DefaultJson, config)
		return HttpF2Client(
			httpClient = httpCLient,
			urlBase
		)
	}
	private fun httpClient(json: Json = F2DefaultJson, config: F2ClientConfigLambda?): HttpClient {
		return HttpClient(CIO) {
			install(ContentNegotiation) {
				json(json)
			}
			config?.let { config(this) }
		}
	}
}

actual fun httpClientBuilder() = HttpClientBuilder()
fun httpClientBuilder(
	json: Json? = F2DefaultJson,
	config: F2ClientConfigLambda? = null
) = HttpClientBuilder(json, config)
