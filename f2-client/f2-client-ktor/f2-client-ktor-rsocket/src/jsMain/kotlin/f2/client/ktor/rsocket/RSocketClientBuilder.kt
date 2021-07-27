package f2.client.ktor.rsocket

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun rsocketClientBuilder(): RSocketClientBuilder {
	return RSocketClientBuilder()
}


actual class RSocketClientBuilder {
	actual fun build(): HttpClient = HttpClient(Js) {
		withRSocket()
	}
}