package city.smartb.f2.client.http.ktor

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.transport.ktor.client.RSocketSupport
import kotlin.time.ExperimentalTime

expect fun rsocketClientBuilder(): RSocketClientBuilder

expect class RSocketClientBuilder {
	fun build(): HttpClient
}

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