package f2.client.ktor.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.serialization.json.Json

@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
	private val json: Json = F2DefaultJson
) {

	actual fun build(urlBase: String): HttpF2Client {
		return HttpF2Client(
			urlBase = urlBase,
			httpClient = httpClient(json)
		)
	}

	private fun httpClient(json: Json = F2DefaultJson): HttpClient {
		return HttpClient(Js) {
			install(ContentNegotiation) {
				json(json)
			}
		}
	}
}

actual fun httpClientBuilder() = HttpClientBuilder()
