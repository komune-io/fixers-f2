package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.http.HttpClientBuilder
import f2.client.ktor.rsocket.RSocketF2ClientBuilder

const val PORT = 7000

suspend fun F2ClientBuilder.get(
	protocol: Protocol,
	host: String,
	port: Int,
	path: String?,
): F2Client {
	return when (protocol) {
		is HTTP -> HttpClientBuilder().build("http", host, port, path)
		is HTTPS -> HttpClientBuilder().build("https", host, port, path)
		is TCP -> RSocketF2ClientBuilder().build("tcp", host, PORT, path, secure = false)
		is WS -> RSocketF2ClientBuilder().build("ws", host, PORT, path, secure = false)
		is WSS -> RSocketF2ClientBuilder().build("wss", host, PORT, path, secure = true)
	}
}
