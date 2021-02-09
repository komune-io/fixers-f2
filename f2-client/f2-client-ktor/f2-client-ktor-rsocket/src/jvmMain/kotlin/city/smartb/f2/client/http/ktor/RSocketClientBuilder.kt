package city.smartb.f2.client.http.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*

actual class RSocketClientBuilder {
	actual fun build(): HttpClient = HttpClient(CIO) {
		withRSocket()
	}
}

actual fun rsocketClientBuilder(): RSocketClientBuilder {
	return RSocketClientBuilder()
}