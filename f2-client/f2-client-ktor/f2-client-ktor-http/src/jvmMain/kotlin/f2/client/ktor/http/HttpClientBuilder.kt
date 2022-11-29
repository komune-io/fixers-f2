package f2.client.ktor.http

import f2.client.F2Client
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class HttpClientBuilder(
	private val json: Json = DefaultJson
) {
	fun build(
		urlBase: String
	): F2Client {
		val httpCLient = httpClient(json)
		return HttpF2Client(
			httpClient = httpCLient,
			urlBase
		)
	}
	private fun httpClient(json: Json = DefaultJson): HttpClient {
		return HttpClient(CIO) {
			install(ContentNegotiation) {
				json(json)
			}
		}
	}
}

actual fun httpClientBuilder() = HttpClientBuilder()
