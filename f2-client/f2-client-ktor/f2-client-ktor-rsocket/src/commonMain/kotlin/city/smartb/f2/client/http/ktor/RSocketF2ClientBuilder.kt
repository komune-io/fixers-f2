package city.smartb.f2.client.http.ktor

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.transport.ktor.client.RSocketSupport
import io.rsocket.kotlin.transport.ktor.client.rSocket
import kotlin.time.ExperimentalTime

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