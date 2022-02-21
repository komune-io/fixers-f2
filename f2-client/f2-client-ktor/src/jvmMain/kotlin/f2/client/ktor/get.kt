package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.http.HttpClientBuilder
import f2.client.ktor.http.httpClientBuilder
import f2.client.ktor.rsocket.RSocketF2ClientBuilder
import f2.client.ktor.rsocket.rSocketF2ClientBuilder
import f2.client.ktor.rsocket.rsocketClientBuilder

suspend fun F2ClientBuilder.get(
	urlBase: String,
): F2Client {
	return when {
		urlBase.startsWith("http:") -> httpClientBuilder().build(urlBase)
		urlBase.startsWith("https:") -> httpClientBuilder().build(urlBase)
		urlBase.startsWith("tcp:") -> rSocketF2ClientBuilder().build(urlBase, false)
		urlBase.startsWith("ws:") -> rSocketF2ClientBuilder().build(urlBase, false)
		urlBase.startsWith("wss:") -> rSocketF2ClientBuilder().build(urlBase, false)
		else -> throw InvalidUrlException(urlBase)

	}
}

suspend fun F2ClientBuilder.get(
	protocol: Protocol,
	host: String,
	port: Int,
	path: String?,
): F2Client {
	val pathA = path?.let { "/$path" } ?: ""
	return when (protocol) {
		is HTTP -> HttpClientBuilder().build("http://$host:$port$pathA")
		is HTTPS -> HttpClientBuilder().build("https://$host:$port$pathA")
		is TCP -> RSocketF2ClientBuilder().build("tcp://$host:$port$pathA", false)
		is WS -> RSocketF2ClientBuilder().build("ws://$host:$port$pathA", false)
		is WSS -> RSocketF2ClientBuilder().build("wss://$host:$port$pathA", true)
	}
}
