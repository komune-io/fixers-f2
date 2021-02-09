package city.smartb.f2.client.http.ktor

import io.ktor.client.*

expect fun rsocketClientBuilder(): RSocketClientBuilder

expect class RSocketClientBuilder {
	fun build(): HttpClient
}