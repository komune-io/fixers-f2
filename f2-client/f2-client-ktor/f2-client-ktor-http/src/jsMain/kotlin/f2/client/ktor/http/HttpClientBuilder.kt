package f2.client.ktor.http

import f2.client.F2Client
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.json.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsExport
@JsName("HttpClientBuilder")
actual class HttpClientBuilder(
) {
	fun build(scheme: String, host: String, port: Int, path: String?): Promise<F2Client> = GlobalScope.promise {
		HttpF2Client(
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
