package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.http.HttpClientBuilder
import f2.client.ktor.http.httpClientBuilder
import f2.client.ktor.rsocket.RSocketF2ClientBuilder
import f2.client.ktor.rsocket.rSocketF2ClientBuilder
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

fun F2ClientBuilder.getHttp(
	url: String,
): F2Client {
	return when {
		url.startsWith("http:") -> httpClientBuilder().build(url)
		url.startsWith("https:") -> httpClientBuilder().build(url)
		else -> throw IllegalArgumentException("Invalid Url[${url}] must start by one of http:, https:, tcp: ws: wss:")
	}
}

suspend fun F2ClientBuilder.get(
	url: String,
): F2Client {
	return when {
		url.startsWith("http:") -> httpClientBuilder().build(url)
		url.startsWith("https:") -> httpClientBuilder().build(url)
		url.startsWith("tcp:") -> rSocketF2ClientBuilder().build(url, false)
		url.startsWith("ws:") -> rSocketF2ClientBuilder().build(url, false)
		url.startsWith("wss:") -> rSocketF2ClientBuilder().build(url, false)
		else -> throw IllegalArgumentException("Invalid Url[${url}] must start by one of http:, https:, tcp: ws: wss:")
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
