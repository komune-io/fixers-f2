package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.client.F2ClientType
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@JsExport
@JsName("RSocketF2Client")
actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {

	override val type: F2ClientType = F2ClientType.RSOCKET

	override fun <RESPONSE> supplier(route: String, typeInfo: TypeInfo) = object : F2Supplier<RESPONSE> {
		@JsExport.Ignore
		override suspend fun invoke(): Flow<RESPONSE> {
			return rSocketClient.requestStream(route).map {
				json.decodeFromString<Response<RESPONSE>>(it).paylaod
			}
		}

	}

	override fun <QUERY, RESPONSE> function(
		route: String,
		queryTypeInfo: TypeInfo,
		responseTypeInfo: TypeInfo,
	) = F2Function<QUERY, RESPONSE> { p1 ->
		p1.map { query ->
			val toSend = handlePayload(query, queryTypeInfo)
			val payload = rSocketClient.requestResponse(route, toSend)
			json.decodeFromString<Response<RESPONSE>>(payload).paylaod
		}
	}

	override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo) = F2Consumer<QUERY> { msg ->
		msg.collect { query ->
			val toSend = handlePayload(query, queryTypeInfo)
			rSocketClient.fireAndForget(route, toSend)
		}
	}

	private val json = Json {
		ignoreUnknownKeys = true
	}

	private fun <T> handlePayload(obj: T, typeInfo: TypeInfo): String {
		val serializer = serializer(typeInfo.kotlinType!!)
		return json.encodeToString(serializer, obj)
	}
}
