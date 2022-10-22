package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.client.F2ClientType
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.promise
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.js.Promise
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.serializer

@JsExport
@JsName("RSocketF2Client")
actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {

	override val type: F2ClientType = F2ClientType.RSOCKET

	override fun <RESPONSE> supplier(route: String, typeInfo: TypeInfo) = object : F2Supplier<RESPONSE> {
		override fun invoke() = GlobalScope.promise {
			rSocketClient.requestStream(route).map {
				json.decodeFromString<Response<RESPONSE>>(it).paylaod
			}.toList().toTypedArray()
		}
	}
	override fun <QUERY, RESPONSE> function(
		route: String,
		queryTypeInfo: TypeInfo,
		responseTypeInfo: TypeInfo,
	) = object : F2Function<QUERY, RESPONSE> {
		override fun invoke(cmd: QUERY) = GlobalScope.promise {
			val toSend = handlePayload(cmd, queryTypeInfo)
			val payload = rSocketClient.requestResponse(route, toSend)
			json.decodeFromString<Response<RESPONSE>>(payload).paylaod
		}
	}


	override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo) = object : F2Consumer<QUERY> {
		override fun invoke(cmd: QUERY): Promise<Unit> = GlobalScope.promise {
			val toSend = handlePayload(cmd, queryTypeInfo)
			rSocketClient.fireAndForget(route, toSend)
		}
	}
	private val json = Json {
		ignoreUnknownKeys = true
	}

	private fun handlePayloadResponse(payload: String): String {
		return json.decodeFromString<Map<String, String>>(payload).get("payload") ?: ""
	}

	private fun <T> handlePayload(obj: T, typeInfo: TypeInfo): String {
		val serializer = serializer(typeInfo.kotlinType!!)
		return json.encodeToString(serializer, obj)
	}
}
