package f2.client.ktor.rsocket

import f2.client.F2Client
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

open class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {
	override suspend fun get(route: String): Flow<String> {
		return rSocketClient.requestStream(route).map(this::handlePayloadResponse)
	}

	override suspend fun accept(route: String, command: String) {
		return rSocketClient.fireAndForget(route, command)
	}

	override suspend fun invoke(route: String, command: String): String {
		val payload = rSocketClient.requestResponse(route, command)
		return handlePayloadResponse(payload)
	}

	private fun handlePayloadResponse(payload: String) =
		Json.decodeFromString<Map<String, String>>(payload).get("payload") ?: ""

}