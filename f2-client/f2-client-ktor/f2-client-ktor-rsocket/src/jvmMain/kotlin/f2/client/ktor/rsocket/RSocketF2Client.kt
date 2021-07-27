package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2FunctionDeclaration
import f2.dsl.fnc.F2SupplierDeclaration
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {

	actual override fun get(route: String) = object : F2SupplierDeclaration<String> {
		override suspend fun invoke(): Flow<String> {
			return rSocketClient.requestStream(route).map {
				handlePayloadResponse(it)
			}
		}
	}

	actual override fun invoke(route: String) = object : F2FunctionDeclaration<String, String> {
		override suspend fun invoke(msg: Flow<String>): Flow<String> {
			return msg.map {cmd ->
				val payload = rSocketClient.requestResponse(route, cmd)
				handlePayloadResponse(payload)
			}
		}
	}

	private fun handlePayloadResponse(payload: String) =
		Json {
			ignoreUnknownKeys = true
		}.decodeFromString<Map<String, String>>(payload).get("payload") ?: ""

}