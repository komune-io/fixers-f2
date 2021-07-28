package f2.client.ktor.rsocket

import io.ktor.client.*

expect fun rsocketClientBuilder(): RSocketClientBuilder

expect class RSocketClientBuilder {
	fun build(): HttpClient
}