package f2.client.ktor.http

import f2.client.F2Client
import f2.client.jsonF2Config
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

actual class HttpClientBuilder {
	private fun httpClient(): HttpClient {
		return HttpClient(CIO) {
			install(ContentNegotiation) {
				json(jsonF2Config)
			}
		}
	}

	fun build(
		urlBase: String
	): F2Client {
		val httpCLient = httpClient()
		return HttpF2Client(
			httpClient = httpCLient,
			urlBase
		)
	}
}

actual fun httpClientBuilder() = HttpClientBuilder()
