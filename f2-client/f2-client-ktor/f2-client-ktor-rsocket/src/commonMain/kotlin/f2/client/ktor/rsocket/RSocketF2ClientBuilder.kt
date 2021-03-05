package f2.client.ktor.rsocket

import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.transport.ktor.client.rSocket

class RSocketF2ClientBuilder {
	suspend fun build(
		scheme: String,
		host: String,
		port: Int,
		path: String? = null,
		secure: Boolean = false
	): RSocketF2Client {
		val rSocket: RSocket = rsocketClientBuilder().build().rSocket(host = host, port = port, path = path ?: "", secure = secure)
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