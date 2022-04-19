package f2.client.ktor.http

import f2.client.F2Client
import f2.client.jsonF2Config
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
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
			install(ContentNegotiation) {
				json(jsonF2Config)
			}
		}
	}

}

actual fun httpClientBuilder() = HttpClientBuilder()
