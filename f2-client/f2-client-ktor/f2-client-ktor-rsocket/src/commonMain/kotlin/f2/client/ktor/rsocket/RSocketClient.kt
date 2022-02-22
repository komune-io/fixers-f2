package f2.client.ktor.rsocket

import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.metadata
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class RSocketClient(
	private val rSocket: RSocket,
) {

	suspend fun fireAndForget(route: String, jsonBody: String) {
		val payload = buildPayload {
			metadata(RoutingMetadata(route))
			data(jsonBody)
		}
		rSocket.fireAndForget(payload)
	}

	fun requestChannel(route: String, init: String, data: Flow<String>): Flow<Payload> {
		val payload = buildPayload {
			metadata(RoutingMetadata(route))
			data(init)

		}
		val payloads = data.map {
			buildPayload {
				data(init)

			}
		}
		return rSocket.requestChannel(payload, payloads)
	}

	suspend fun requestResponse(route: String, jsonBody: String? = null): String {
		val payload = buildPayload {
			metadata(RoutingMetadata(route))
			if (jsonBody != null) {
				data(jsonBody)
			}
		}
		val dd = rSocket.requestResponse(payload)
		return dd.data.readText()
	}

	fun requestStream(route: String, jsonBody: String? = null): Flow<String> {
		val payload = buildPayload {
			metadata(RoutingMetadata(route))
			if (jsonBody != null) {
				data(jsonBody)
			} else data("")

		}
		return rSocket.requestStream(payload).map {
			it.data.readText()
		}
	}
}
