package city.smartb.f2.client.ktor

import city.smartb.f2.client.http.ktor.RSocketF2ClientBuilder
import city.smartb.f2.client.http.ktor.httpClientBuilder
import f2.client.F2Client

class F2ClientBuilder {
	companion object
}

suspend fun F2ClientBuilder.Companion.get(
	protocol: Protocol,
	host: String,
	port: Int,
	path: String? = null
): F2Client {
	return when(protocol) {
		is  HTTP-> httpClientBuilder().build("http", host, port, path)
		is  HTTPS-> httpClientBuilder().build("https", host, port, path)
		is  TCP-> RSocketF2ClientBuilder().build("tcp", host, 7000, path)
		is  WS-> RSocketF2ClientBuilder().build("ws", host, 7000, path, secure = false)
		is  WSS-> RSocketF2ClientBuilder().build("wss", host, 7000, path, secure = true)
	}
}

