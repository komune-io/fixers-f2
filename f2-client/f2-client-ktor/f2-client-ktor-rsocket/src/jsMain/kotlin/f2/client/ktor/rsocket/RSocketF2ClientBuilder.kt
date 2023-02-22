package f2.client.ktor.rsocket

import f2.client.F2Client
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.ktor.client.rSocket
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual fun rSocketF2ClientBuilder(): RSocketF2ClientBuilder = RSocketF2ClientBuilder()

actual class RSocketF2ClientBuilder {
	fun build(
		url: String,
		secure: Boolean,
	): Promise<F2Client> = GlobalScope.promise {
		val rSocket: RSocket =
			rsocketClientBuilder().build().rSocket(url, secure = secure)
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
