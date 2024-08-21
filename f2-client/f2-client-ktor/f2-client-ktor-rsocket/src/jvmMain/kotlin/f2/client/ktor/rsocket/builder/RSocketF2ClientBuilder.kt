package f2.client.ktor.rsocket.builder

import f2.client.ktor.rsocket.RSocketClient
import f2.client.ktor.rsocket.RSocketF2Client
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.ktor.client.rSocket

actual fun rSocketF2ClientBuilderDefault() = RSocketF2ClientBuilder()

actual class RSocketF2ClientBuilder {

	actual suspend fun build(
		url: String,
		secure: Boolean,
	): RSocketF2Client {
		val rSocket: RSocket =
			rsocketClientBuilderDefault().build().rSocket(url, secure = secure)
		val client = RSocketClient(rSocket)
		return RSocketF2Client(client)
	}

	actual suspend fun RSocketClientBuilder.getClient(
		baseUrl: String,
		bearerJwt: String?,
	): RSocketClient {
		val rSocket: RSocket = build().rSocket(baseUrl, false)
		return RSocketClient(rSocket)
	}
}
