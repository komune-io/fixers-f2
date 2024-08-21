package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.payload.PayloadMimeType
import kotlin.time.ExperimentalTime
import kotlinx.serialization.json.Json

//fun rsocketClientBuilder(): RSocketClientBuilder
//
//class RSocketClientBuilder {
//	fun build(): HttpClient
//}

@OptIn(ExperimentalTime::class)
fun HttpClientConfig<*>.withConfig(
	json: Json? = null,
	config: F2ClientConfigLambda<*>? = null
) {
	json?.let {
		install(ContentNegotiation) {
			json(json)
		}
	}

	config?.let { it(this) }
}

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
