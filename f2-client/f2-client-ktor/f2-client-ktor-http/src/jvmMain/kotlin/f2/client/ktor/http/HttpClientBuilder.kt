package f2.client.ktor.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class HttpClientBuilder(
	private val json: Json? = F2DefaultJson,
	private val config: F2ClientConfigLambda<CIOEngineConfig>? = {}
) {
	actual fun build(urlBase: String): HttpF2Client {
		val httpClient = httpClient()
		return HttpF2Client(
			httpClient = httpClient,
			urlBase = urlBase
		)
	}

	private fun httpClient(): HttpClient {
		return HttpClient(CIO) {
			json?.let {
				install(ContentNegotiation) {
					json(it)
				}
			}
			config?.let { it(this) }
		}
	}
}
