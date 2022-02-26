package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {

	private val json: Json = Json {
		ignoreUnknownKeys = true
	}

	private fun handlePayloadResponse(payload: String): String {
		val json = json.parseToJsonElement(payload).jsonObject.get("payload")
		return when (json) {
			is JsonPrimitive -> json.content
			is JsonArray -> json.toString()
			else -> json.toString()
		}
	}

	actual override fun supplier(route: String) = F2Supplier {
		rSocketClient.requestStream(route).flatMapMerge {
			val resp = handlePayloadResponse(it)
			flowOf(resp)
		}
	}

	actual override fun function(route: String): F2Function<String, String> = F2Function { msgs ->
		msgs.map { msg ->
			val payload = rSocketClient.requestResponse(route, msg)
			handlePayloadResponse(payload)
		}
	}

	actual override fun consumer(route: String): F2Consumer<String> = F2Consumer { msgs ->
		msgs.map { msg ->
			rSocketClient.fireAndForget(route, msg)
		}.collect()
	}
}
