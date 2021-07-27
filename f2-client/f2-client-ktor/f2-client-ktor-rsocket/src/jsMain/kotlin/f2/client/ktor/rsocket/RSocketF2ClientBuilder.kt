package f2.client.ktor.rsocket

import f2.client.F2Client
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.transport.ktor.client.rSocket
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

actual fun rSocketF2ClientBuilder(): RSocketF2ClientBuilder = RSocketF2ClientBuilder()

actual class RSocketF2ClientBuilder {
	fun build(
		scheme: String,
		host: String,
		port: Int,
		path: String?,
		secure: Boolean,
	): Promise<F2Client> = GlobalScope.promise {
		val rSocket: RSocket = rsocketClientBuilder().build().rSocket(host = host, port = port, path = path ?: "", secure = secure)
		val client = RSocketClient(rSocket)
		RSocketF2Client(client)
	}

	suspend fun RSocketClientBuilder.getClient(
		baseUrl: String,
		bearerJwt: String?,
	): RSocketClient {
		val rSocket: RSocket = build().rSocket(baseUrl, false)
		return RSocketClient(rSocket)
	}
}
