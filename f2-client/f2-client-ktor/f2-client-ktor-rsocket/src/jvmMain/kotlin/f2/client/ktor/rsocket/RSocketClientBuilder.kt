package f2.client.ktor.rsocket

import io.ktor.client.engine.cio.*
import io.ktor.client.*

actual fun rsocketClientBuilder(): RSocketClientBuilder {
	return RSocketClientBuilder()
}

actual class RSocketClientBuilder {
	actual fun build(): HttpClient = HttpClient(CIO) {
		withRSocket()
	}
}

