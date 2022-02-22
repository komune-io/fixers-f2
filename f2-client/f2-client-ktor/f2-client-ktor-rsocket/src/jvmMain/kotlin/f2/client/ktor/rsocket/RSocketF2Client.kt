package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {


	private val json: Json = Json {
		ignoreUnknownKeys = true
	}


	private fun handlePayloadResponse(payload: String): List<String> {
		try {
			return json.decodeFromString<Payload<String>>(payload).payload.let {
				listOf(it)
			}
		} catch (e: Exception) {
			return json.decodeFromString<Payload<List<String>>>(payload).payload
		}
	}

	actual override fun supplier(route: String) = F2Supplier {
		rSocketClient.requestStream(route).flatMapMerge {
			handlePayloadResponse(it).asFlow()
		}
	}

	actual override fun function(route: String): F2Function<String, String> = F2Function { msgs ->
		val json = json.encodeToString(msgs.toList())
		val payload = rSocketClient.requestResponse(route, json)
		handlePayloadResponse(payload).asFlow()
	}

	actual override fun consumer(route: String): F2Consumer<String> = F2Consumer { msgs ->
		val json = json.encodeToString(msgs.toList())
		rSocketClient.fireAndForget(route, json)
	}
}

@Serializable
class Payload<T>(
	val payload: T
)
