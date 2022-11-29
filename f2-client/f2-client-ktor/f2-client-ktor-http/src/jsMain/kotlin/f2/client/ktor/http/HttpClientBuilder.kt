package f2.client.ktor.http

import f2.client.F2Client
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.serialization.json.Json

@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
	private val json: Json = DefaultJson
) {
	fun build(scheme: String, host: String, port: Int, path: String?): Promise<F2Client> = GlobalScope.promise {
		HttpF2Client(
			scheme = scheme,
			host = host,
			port = port,
			path = path ?: "",
			httpClient = httpClient(json)
		)
	}

	private fun httpClient(json: Json = DefaultJson): HttpClient {
		return HttpClient(Js) {
			install(ContentNegotiation) {
				json(json)
			}
		}
	}

}

actual fun httpClientBuilder() = HttpClientBuilder()
