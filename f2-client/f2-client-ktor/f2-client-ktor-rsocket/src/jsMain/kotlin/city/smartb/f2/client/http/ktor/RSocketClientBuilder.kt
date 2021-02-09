package city.smartb.f2.client.http.ktor

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual class RSocketClientBuilder {
	actual fun build(): HttpClient = HttpClient(Js) {
		withRSocket()
	}
}

actual fun rsocketClientBuilder(): RSocketClientBuilder {
	return RSocketClientBuilder()
}
