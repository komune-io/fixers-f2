package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.http.HttpClientBuilder
import f2.client.ktor.rsocket.RSocketF2ClientBuilder
import kotlin.js.Promise

fun F2ClientBuilder.Companion.get(
	protocol: Protocol,
	host: String,
	port: Int,
	path: String?,
): Promise<F2Client> {
	return when(protocol) {
		is HTTP -> HttpClientBuilder().build("http", host, port, path)
		is HTTPS -> HttpClientBuilder().build("https", host, port, path)
		is TCP -> RSocketF2ClientBuilder().build("tcp", host, 7000, path, secure = false)
		is WS -> RSocketF2ClientBuilder().build("ws", host, 7000, path, secure = false)
		is WSS -> RSocketF2ClientBuilder().build("wss", host, 7000, path, secure = true)
	}
}