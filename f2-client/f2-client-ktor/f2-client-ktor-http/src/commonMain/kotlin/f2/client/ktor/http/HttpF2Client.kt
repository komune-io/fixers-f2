package f2.client.ktor.http

import f2.client.F2Client
import f2.client.F2ClientType
import f2.client.ktor.common.F2DefaultJson
import f2.client.ktor.http.model.F2UploadCommand
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.error.refusalCode
import f2.dsl.cqrs.exception.F2Exception
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
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
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.cancellation.CancellationException
import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.io.IOException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.serializer

@JsExport
class HttpF2Client @JsExport.Ignore constructor(
	@property:JsExport.Ignore
	val httpClient: HttpClient,
	val urlBase: String,
	@property:JsExport.Ignore
	val json: Json = F2DefaultJson
) : F2Client {

	override val type: F2ClientType = F2ClientType.HTTP

	private fun <T> handlePayloadResponse(response: HttpResponse, typeInfo: TypeInfo): Flow<T> {
		return flow {
			if (!response.status.isSuccess()) {
				throw json.responseError(response)
			}
			emitPayload(response.body<T>(typeInfo))
		}
	}

	@JsExport.Ignore
	override fun <RESPONSE> supplier(
		route: String, responseTypeInfo: TypeInfo
	): F2Supplier<RESPONSE> = F2Supplier<RESPONSE> {
		send { httpClient.get(buildUrl(route)) }.let { response ->
			handlePayloadResponse(response, responseTypeInfo)
		}
	}

	@JsExport.Ignore
	override fun <QUERY, RESPONSE> function(
		route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo
	) = F2Function<QUERY, RESPONSE> { messageFlow ->
		doPost(route, queryTypeInfo, messageFlow).asFlow().transform { response ->
			if (!response.status.isSuccess()) {
				throw json.responseError(response)
			}
			emitPayload(response.body<RESPONSE>(responseTypeInfo))
		}
	}

	@JsExport.Ignore
	override fun <QUERY> consumer(
		route: String, queryTypeInfo: TypeInfo
	): F2Consumer<QUERY> = F2Consumer { messages ->
		doPost(route, queryTypeInfo, messages)
			.firstOrNull { !it.status.isSuccess() }
			?.let { throw json.responseError(it) }
	}

	private suspend fun <MSG> doPost(route: String, queryTypeInfo: TypeInfo, messages: Flow<MSG>): List<HttpResponse> {
		val isListType = queryTypeInfo.type == List::class
		val isF2UploadCommand = queryTypeInfo.type == F2UploadCommand::class
		return if (isF2UploadCommand) {
			messages.map { msg ->
				postFormData(route, queryTypeInfo, msg as F2UploadCommand<*>)
			}.toList()
		} else if (isListType) {
			listOf(postJson(route, queryTypeInfo, messages))
		} else {
			messages.map { msg ->
				postJson(route, queryTypeInfo, msg)
			}.toList()
		}
	}

	private suspend fun <MSG> postJson(route: String, queryTypeInfo: TypeInfo, message: MSG): HttpResponse {
		return send {
			httpClient.post(buildUrl(route)) {
				contentType(ContentType.Application.Json)
				setBody(message, queryTypeInfo)
			}
		}
	}

	private suspend fun <MSG> postJson(route: String, queryTypeInfo: TypeInfo, messages: Flow<MSG>): HttpResponse {
		return send {
			httpClient.post(buildUrl(route)) {
				contentType(ContentType.Application.Json)
				setBody(messages.toList(), queryTypeInfo)
			}
		}
	}

	private suspend fun <MSG> postFormData(
		route: String, queryTypeInfo: TypeInfo, message: F2UploadCommand<MSG>
	): HttpResponse {
		return send {
			httpClient.submitFormWithBinaryData(
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
	}

	private fun buildUrl(route: String) = "$urlBase/${route}"
}

/** The response [request] answers, an error one included: a client expecting success throws it instead. */
internal suspend fun send(request: suspend () -> HttpResponse): HttpResponse = try {
	request()
} catch (e: ResponseException) {
	if (e.response.status.isSuccess()) throw e
	e.response
}

private suspend fun <T> FlowCollector<T>.emitPayload(result: T) {
	if (result is Collection<*>) {
		@Suppress("UNCHECKED_CAST") // a list response decoded as T: its items are the T the caller asked for, erased
		(result as Collection<T>).forEach { emit(it) }
	} else {
		emit(result)
	}
}

/** The F2 error a failed response answers, read from its body; a body the client cannot read keeps the status. */
internal suspend fun Json.responseError(response: HttpResponse): F2Exception {
	val status = response.status.value
	return try {
		F2Exception(error = decodeError(response.bodyAsText(), status))
	} catch (e: CancellationException) {
		throw e
	} catch (e: IOException) {
		// a body cut while read, or not decodable in its charset
		F2Exception(error = F2Error(message = e.message.orEmpty(), code = status), cause = e)
	} catch (e: IllegalArgumentException) {
		// a charset the platform does not know
		F2Exception(error = F2Error(message = e.message.orEmpty(), code = status), cause = e)
	}
}

/**
 * The error an F2 server's error body names — or, for `code` and `id`, a body written before 0.47. Only a body
 * with a `message` is a refusal's: any other names the meaning of its status in `refusal`, which [refusalCode]
 * derives from the status again. A body that is no JSON object is the message itself.
 */
@OptIn(ExperimentalTime::class)
private fun Json.decodeError(body: String, status: Int): F2Error {
	val fields = try {
		parseToJsonElement(body) as? JsonObject
	} catch (_: SerializationException) {
		null
	} ?: return F2Error(message = body, code = status)
	val message = fields.text("message")
	return F2Error(
		message = message ?: fields.text("error") ?: body,
		id = fields.text("id"),
		timestamp = fields.text("timestamp") ?: Clock.System.now().toString(),
		code = (fields["code"] as? JsonPrimitive)?.intOrNull ?: status,
		requestId = fields.text("requestId"),
		refusal = fields.text("refusal")?.takeIf { message != null }?.let { code ->
			F2Refusal(
				code = code,
				parameters = decodeOrNull<Map<String, String>>(fields["parameters"]).orEmpty(),
				citers = decodeOrNull<List<String>>(fields["citers"]).orEmpty(),
			)
		},
	)
}

private fun JsonObject.text(key: String): String? = (this[key] as? JsonPrimitive)?.takeIf { it.isString }?.content

private inline fun <reified T> Json.decodeOrNull(element: JsonElement?): T? = try {
	element?.let { decodeFromJsonElement<T>(it) }
} catch (_: SerializationException) {
	null
} catch (_: IllegalArgumentException) {
	null
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

	@Suppress("UNCHECKED_CAST") // T is erased: the serializer is looked up from its TypeInfo
	private val querySerializer: KSerializer<T> =
		queryTypeInfo.kotlinType?.let { serializer(it) } as KSerializer<T>

	private fun T.toJson(): String = json.encodeToString(querySerializer, this)
}
