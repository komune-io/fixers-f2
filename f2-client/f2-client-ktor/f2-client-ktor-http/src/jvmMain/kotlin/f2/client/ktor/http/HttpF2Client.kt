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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

actual open class HttpF2Client(
	private val httpClient: HttpClient,
	private val urlBase: String,
) : F2Client {

	private val json: Json = Json {
		ignoreUnknownKeys = true
	}

	private fun handlePayloadResponse(json: JsonElement): List<String> {
		return when (json) {
			is JsonPrimitive -> listOf(json.toString())
			is JsonArray -> json.map { it.toString() }
			else -> listOf(json.toString())
		}
	}

	actual override fun supplier(route: String) = F2Supplier<String> {
		flow {
			httpClient.get<JsonElement>("$urlBase/${route}").let { element ->
				handlePayloadResponse(element)
			}.forEach {
				emit(it)
			}
		}
	}

	actual override fun function(route: String) = F2Function<String, String> { msg ->
		flow {
			httpClient.post<JsonElement>("$urlBase/${route}") {
				contentType(ContentType.Application.Json)
				body = msg.toList()
			}.let { element ->
				handlePayloadResponse(element)
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
