package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.contentType
import io.ktor.http.ContentType
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

actual open class HttpF2Client(
	private val httpClient: HttpClient,
	private val urlBase: String,
) : F2Client {

	actual override fun supplier(route: String) = F2Supplier<String> {
		flow {
			httpClient.get<List<String>>("$urlBase/${route}").forEach {
				emit(it)
			}
		}
	}

	actual override fun function(route: String) = F2Function<String, String> { msg ->
		flow {
			httpClient.post<List<String>>("$urlBase/${route}") {
				contentType(ContentType.Application.Json)
				body = msg.toList()
			}.forEach {
				emit(it)
			}
		}
	}

	actual override fun consumer(route: String) = F2Consumer<String> { msg ->
		httpClient.post("$urlBase/${route}") {
			contentType(ContentType.Application.Json)
			body = msg.toList()
		}
	}

	fun getT(route: String) = F2Supplier {
		flow {
			val tt = httpClient.get<String>("$urlBase/${route}")
			emit(tt)
		}
	}
}
