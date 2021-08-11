package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {

	actual override fun get(route: String) = F2Supplier {
		rSocketClient.requestStream(route).map {
			handlePayloadResponse(it)
		}
	}

	actual override fun invoke(route: String) = F2Function<String, String> { msg ->
		msg.map { cmd ->
			val payload = rSocketClient.requestResponse(route, cmd)
			handlePayloadResponse(payload)
		}
	}

	private val json: Json = Json {
		ignoreUnknownKeys = true
	}

	private fun handlePayloadResponse(payload: String): String {
		return json.decodeFromString<Map<String, String>>(payload)["payload"] ?: ""
	}
}