package f2.client.ktor.rsocket

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js

actual fun rsocketClientBuilder(): RSocketClientBuilder {
	return RSocketClientBuilder()
}


actual class RSocketClientBuilder {
	actual fun build(): HttpClient = HttpClient(Js) {
		withRSocket()
	}
}
