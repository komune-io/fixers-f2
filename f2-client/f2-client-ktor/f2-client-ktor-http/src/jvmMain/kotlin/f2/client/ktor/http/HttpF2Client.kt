package f2.client.ktor.http

import f2.client.F2Client
import f2.client.F2ClientType
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo
import java.util.Date
import java.util.UUID
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

actual open class HttpF2Client(
	protected val httpClient: HttpClient,
	protected val urlBase: String,
) : F2Client {

	override val type: F2ClientType = F2ClientType.HTTP

	private suspend fun handlePayloadResponseToStringList(response: HttpResponse): List<String> {
		if(!response.status.isSuccess()) {
			handleError(response)
		}
		return 	when (val element = response.body<JsonElement>()) {
			is JsonPrimitive -> listOf(element.toString())
			is JsonArray -> element.map { it.toString() }
			else -> listOf(element.toString())
		}
	}

	suspend fun handleError(response: HttpResponse) {
		val error: F2Error = try {
			response.body()
		} catch (e: Throwable) {
			F2Error(
				id = UUID.randomUUID().toString(),
				timestamp = Date().toString(),
				code = response.status.value,
				message = response.bodyAsText()
			)
		}
		println(response.status)
		println(error)
		println("/////////////////////////////")
		throw F2Exception(error = error)
	}

	suspend inline fun <reified T> handlePayloadResponse(response: HttpResponse, typeInfo: TypeInfo): T {
		if(!response.status.isSuccess()) {
			handleError(response)
		}
		return response.body(typeInfo)
	}

	override fun <RESPONSE> supplier(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE> = F2Supplier<RESPONSE> {
		flow {
			httpClient.get("$urlBase/${route}").let { response ->
				handlePayloadResponse<List<RESPONSE>>(response, responseTypeInfo)
			}.forEach {
				emit(it)
			}
		}
	}
	override fun <QUERY, RESPONSE> function(
		route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo
	) = F2Function<QUERY, RESPONSE> { msg ->
		flow {
			httpClient.post("$urlBase/${route}") {
				contentType(ContentType.Application.Json)
				setBody(msg.toList(), queryTypeInfo)
			}.let { response ->
				handlePayloadResponse<List<RESPONSE>>(response, responseTypeInfo)
			}.forEach {
				emit(it)
			}
		}
	}
	override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>  = F2Consumer { msg ->
		httpClient.post("$urlBase/${route}") {
			contentType(ContentType.Application.Json)
			setBody(msg.toList(), queryTypeInfo)
		}
	}

	fun getT(route: String) = F2Supplier {
		flow {
			val tt = httpClient.get("$urlBase/${route}")
			emit(tt.bodyAsText())
		}
	}

}
