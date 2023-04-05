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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

actual open class HttpF2Client(
	actual val httpClient: HttpClient,
	val urlBase: String,
) : F2Client {

	override val type: F2ClientType = F2ClientType.HTTP

	private suspend fun handleError(response: HttpResponse) {
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
		throw F2Exception(error = error)
	}

	private suspend inline fun <T> handlePayloadResponse(response: HttpResponse, typeInfo: TypeInfo): Flow<T> {
		return flow {
			try {
				if (!response.status.isSuccess()) {
					handleError(response)
				}
				response.body<T>(typeInfo).let { result ->
					if(result is Collection<*>) {
						result.forEach {emit(it as T)}
					} else {
						emit(result)
					}
				}
			} catch (e: Exception) {
				throw e;
			}
		}
	}

	override fun <RESPONSE> supplier(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE> = F2Supplier<RESPONSE> {
		httpClient.get("$urlBase/${route}").let { response ->
			handlePayloadResponse(response, responseTypeInfo)
		}
	}

	override fun <QUERY, RESPONSE> function(
		route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo
	) = F2Function<QUERY, RESPONSE> { msg ->
		httpClient.post("$urlBase/${route}") {
			contentType(ContentType.Application.Json)
			setBody(msg.toList(), queryTypeInfo)
		}.let { response ->
			handlePayloadResponse<RESPONSE>(response, responseTypeInfo)
		}
	}

	override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>  = F2Consumer { msg ->
		httpClient.post("$urlBase/${route}") {
			contentType(ContentType.Application.Json)
			setBody(msg.toList(), queryTypeInfo)
		}
	}

}
