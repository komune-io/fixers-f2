package f2.client.ktor.rsocket.builder

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.websocket.WebSockets
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.payload.PayloadMimeType

fun HttpClientConfig<*>.applyRSocket() {
	install(WebSockets)
	install(RSocketSupport) {
		connector = RSocketConnector {
			connectionConfig {
				payloadMimeType = PayloadMimeType(
					data = "application/json",
					metadata = "message/x.rsocket.routing.v0"
				)
			}
		}
	}
}
