package city.smartb.f2.client.http.ktor

import city.smartb.f2.dsl.cqrs.S2CQRSClient
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.json.*

@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
) {
	actual fun cqrsClient(scheme: String, host: String, port: Int, path: String?): S2CQRSClient {
		return HttpCQRSClient(
			scheme = scheme,
			host = host,
			port = port,
			path = path ?: "",
			httpClient = httpClient()
		)
	}

	private fun httpClient(): HttpClient {
		return HttpClient(Js) {
			install(JsonFeature)
		}
	}

}

actual fun httpClientBuilder() = HttpClientBuilder()
