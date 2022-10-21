package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.client.F2ClientType
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import kotlin.reflect.full.createType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {

	override val type: F2ClientType = F2ClientType.RSOCKET

	private val json: Json = Json {
		ignoreUnknownKeys = true
	}

	override fun <RESPONSE> supplierGen(route: String, responseTypeInfo: TypeInfo) = F2Supplier<RESPONSE> {
		rSocketClient.requestStream(route).flatMapMerge {payload ->
			handlePayloadResponse<RESPONSE>(payload, responseTypeInfo)
		}
	}

	override fun <QUERY, RESPONSE> functionGen(
		route: String,
		queryTypeInfo: TypeInfo,
		responseTypeInfo: TypeInfo,
	) = F2Function<QUERY, RESPONSE> { msgs ->
		msgs.flatMapMerge { msg ->
			val toSend = encode(msg, queryTypeInfo)
			val payload = rSocketClient.requestResponse(route, toSend)
			handlePayloadResponse(payload, responseTypeInfo)
		}
	}

	override fun <QUERY> consumerGen(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY> = F2Consumer { msgs ->
		msgs.map { msg ->
			val toSend = encode(msg, queryTypeInfo)
			rSocketClient.fireAndForget(route, toSend)
		}.collect()
	}

	private fun <T> encode(obj: T, typeInfo: TypeInfo): String {
		val serializer = serializer(typeInfo.kotlinType!!)
		return json.encodeToString(serializer, obj)
	}
	private fun <T> decode(value: JsonElement, typeInfo: TypeInfo): T {
		val serializer = serializer(typeInfo.kotlinType!!) as KSerializer<T>
		return json.decodeFromJsonElement(serializer, value)
	}

	private fun handlePayloadResponse(payload: String): String {
		val json = json.parseToJsonElement(payload).jsonObject["payload"]
		return when (json) {
			is JsonPrimitive -> json.content
			is JsonArray -> json.toString()
			else -> json.toString()
		}
	}
	private fun <RESPONSE> handlePayloadResponse(payload: String, typeInfo: TypeInfo): Flow<RESPONSE> = flow {
		json.parseToJsonElement(payload).jsonObject["payload"]?.let { jsonElement ->
			emit(decode(jsonElement, typeInfo))
		}
	}
}
