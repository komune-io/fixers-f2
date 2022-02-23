package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {


	private val json: Json = Json {
		ignoreUnknownKeys = true
	}


	private fun handlePayloadResponse(payload: String): List<String> {
		return try {
			json.decodeFromString<Payload<String>>(payload).payload.let {
				listOf(it)
			}
		} catch (e: Exception) {
			json.decodeFromString<Payload<List<String>>>(payload).payload
		}
	}

	actual override fun supplier(route: String) = F2Supplier {
		rSocketClient.requestStream(route).flatMapMerge {
			handlePayloadResponse(it).asFlow()
		}
	}

	actual override fun function(route: String): F2Function<String, String> = F2Function { msgs ->
		msgs.map { msg ->
			val payload = rSocketClient.requestResponse(route, msg)
			handlePayloadResponse(payload).first()
		}
	}

	actual override fun consumer(route: String): F2Consumer<String> = F2Consumer { msgs ->
		msgs.map { msg ->
			rSocketClient.fireAndForget(route, msg)
		}.collect()
	}
}

@Serializable
class Payload<T>(
	val payload: T
)
