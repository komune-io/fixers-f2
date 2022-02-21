package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {


	private val json: Json = Json {
		ignoreUnknownKeys = true
	}

	private fun handlePayloadResponse(payload: String): String {
		return json.decodeFromString<Map<String, String>>(payload)["payload"] ?: ""
	}

	actual override fun supplier(route: String) = F2Supplier {
		rSocketClient.requestStream(route).map {
			handlePayloadResponse(it)
		}
	}

	actual override fun function(route: String): F2Function<String, String> = F2Function<String, String> { msg ->
		msg.map { cmd ->
			val payload = rSocketClient.requestResponse(route, cmd)
			handlePayloadResponse(payload)
		}
	}

	actual override fun consumer(route: String): F2Consumer<String> = F2Consumer<String> { msgs ->
		msgs.map { msg ->
			rSocketClient.fireAndForget(route, msg)
		}
	}
}
