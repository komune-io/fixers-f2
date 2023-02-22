package f2.client.ktor.rsocket

import f2.client.F2Client
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.ktor.client.rSocket

actual fun rSocketF2ClientBuilder() = RSocketF2ClientBuilder()

actual class RSocketF2ClientBuilder {
	suspend fun build(
		url: String,
		secure: Boolean,
	): F2Client {
		val rSocket: RSocket =
			rsocketClientBuilder().build().rSocket(url, secure = secure)
		val client = RSocketClient(rSocket)
		return RSocketF2Client(client)
	}

	suspend fun RSocketClientBuilder.getClient(
		baseUrl: String,
		bearerJwt: String?,
	): RSocketClient {
		val rSocket: RSocket = build().rSocket(baseUrl, false)
		return RSocketClient(rSocket)
	}
}
