package f2.client.ktor.http

import f2.client.F2Client
import f2.client.F2ClientType
import f2.client.ktor.common.F2DefaultJson
import f2.client.ktor.http.model.F2UploadCommand
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.ChannelProvider
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import io.ktor.utils.io.ByteReadChannel
import java.util.Date
import java.util.UUID
import kotlin.reflect.full.isSubclassOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

actual open class HttpF2Client(
	actual val httpClient: HttpClient,
	actual val urlBase: String,
	val json: Json = F2DefaultJson
) : F2Client {

	actual override val type: F2ClientType = F2ClientType.HTTP

	@Suppress("SwallowedException")
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
			if (!response.status.isSuccess()) {
				handleError(response)
			}
			response.body<T>(typeInfo).let { result ->
				if (result is Collection<*>) {
					result.forEach { emit(it as T) }
				} else {
					emit(result)
				}
			}
		}
	}

	actual override fun <RESPONSE> supplier(
		route: String, responseTypeInfo: TypeInfo
	): F2Supplier<RESPONSE> = F2Supplier<RESPONSE> {
		httpClient.get("$urlBase/${route}").let { response ->
			handlePayloadResponse(response, responseTypeInfo)
		}
	}

	actual override fun <QUERY, RESPONSE> function(
		route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo
	) = F2Function<QUERY, RESPONSE> { messageFlow ->
		doPost(route, queryTypeInfo, messageFlow).asFlow().transform { response ->
			if (!response.status.isSuccess()) {
				handleError(response)
			}
			response.body<RESPONSE>(responseTypeInfo).let { result ->
				if (result is Collection<*>) {
					result.forEach { emit(it as RESPONSE) }
				} else {
					emit(result)
				}
			}
		}
	}

	inline fun <reified MSG, reified RESPONSE> function(route: String): F2Function<MSG, RESPONSE> {
		return if (MSG::class.isSubclassOf(F2UploadCommand::class)) {
			function(route, typeInfo<MSG>(), typeInfo<RESPONSE>())
		} else {
			function(route, typeInfo<List<MSG>>(), typeInfo<List<RESPONSE>>())
		}
	}

	actual override fun <QUERY> consumer(
		route: String, queryTypeInfo: TypeInfo
	): F2Consumer<QUERY> = F2Consumer { messages ->
		doPost(route, queryTypeInfo, messages)
	}

	private suspend fun <MSG> doPost(route: String, queryTypeInfo: TypeInfo, messages: Flow<MSG>): List<HttpResponse> {
		val isListType = queryTypeInfo.type == List::class
		return if (queryTypeInfo.type.isSubclassOf(F2UploadCommand::class)) {
			messages.map { msg ->
				postFormData(route,queryTypeInfo, msg as F2UploadCommand<*>)
			}.toList()
		} else if(isListType) {
			listOf(postJson(route, queryTypeInfo, messages))
		} else {
			messages.map { msg ->
				postJson(route, queryTypeInfo, msg)
			}.toList()
		}
	}

	private suspend fun <MSG> postJson(route: String, queryTypeInfo: TypeInfo, message: MSG): HttpResponse {
		return httpClient.post(buildUrl(route)) {
			contentType(ContentType.Application.Json)
			setBody(message, queryTypeInfo)
		}
	}
	private suspend fun <MSG> postJson(route: String, queryTypeInfo: TypeInfo, messages: Flow<MSG>): HttpResponse {
		return httpClient.post(buildUrl(route)) {
			contentType(ContentType.Application.Json)
			setBody(messages.toList(), queryTypeInfo)
		}
	}

	private suspend fun <MSG> postFormData(
		route: String, queryTypeInfo: TypeInfo, message: F2UploadCommand<MSG>
	): HttpResponse {
		return httpClient.submitFormWithBinaryData(
			url = buildUrl(route),
			formData = FormDataBodyBuilder<MSG>(json, queryTypeInfo).apply {
				param("command", message.command)
				message.fileMap.forEach { (key, files) ->
					files.forEach { file ->
						file(key, file.content, file.name, file.contentType)
					}
				}
			}.toFormData()
		)
	}

	private fun buildUrl(route: String) = "$urlBase/${route}"
}

private class FormDataBodyBuilder<T>(
    val json: Json = F2DefaultJson,
    queryTypeInfo: TypeInfo
) {
	private val formParts = mutableListOf<FormPart<*>>()

	fun toFormData() = formData { formParts.forEach { append(it) } }

	fun param(key: String, value: String, contentType: String? = null) {
		val headers = contentType
			?.let { Headers.build { append(HttpHeaders.ContentType, contentType) } }
			?: Headers.Empty

		FormPart(
			key = key,
			value = value,
			headers = headers
		).let(formParts::add)
	}

	fun param(key: String, value: T) {
		param(key, value.toJson(), "application/json")
	}

	fun file(key: String, content: ByteReadChannel, filename: String, contentType: String?) {
		FormPart(
			key = key,
			value = ChannelProvider { content },
			headers = Headers.build {
				append(HttpHeaders.ContentDisposition, "filename=$filename")
				contentType?.let { append(HttpHeaders.ContentType, it) }
			}
		).let(formParts::add)
	}

	private val querySerializer: KSerializer<T> =
		queryTypeInfo.kotlinType?.let { serializer(it) } as KSerializer<T>

	private fun T.toJson(): String = json.encodeToString(querySerializer,this)
}
