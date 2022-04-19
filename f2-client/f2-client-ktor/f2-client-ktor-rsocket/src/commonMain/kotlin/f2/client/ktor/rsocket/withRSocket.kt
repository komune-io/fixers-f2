package f2.client.ktor.rsocket

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.websocket.WebSockets
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.payload.PayloadMimeType
import kotlin.time.ExperimentalTime

//fun rsocketClientBuilder(): RSocketClientBuilder
//
//class RSocketClientBuilder {
//	fun build(): HttpClient
//}

@OptIn(ExperimentalTime::class)
fun HttpClientConfig<*>.withRSocket() {
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
