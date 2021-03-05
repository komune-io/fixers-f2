package f2.client.ktor.http

import f2.client.F2Client
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.json.*

@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
) {
	actual fun build(scheme: String, host: String, port: Int, path: String?): F2Client {
		return HttpF2Client(
			scheme = scheme,
			host = host,
			port = port,
			path = path ?: "",
			httpClient = httpClient()
		)
	}

	private fun httpClient(): io.ktor.client.HttpClient {
		return HttpClient(Js) {
			install(JsonFeature)
		}
	}

}

actual fun httpClientBuilder() = f2.client.ktor.http.HttpClientBuilder()
