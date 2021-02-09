package city.smartb.f2.client.ktor

import city.smartb.f2.client.http.ktor.cqrsClient
import city.smartb.f2.client.http.ktor.httpClientBuilder
import city.smartb.f2.client.http.ktor.rsocketClientBuilder

class S2ClientBuilder {
	companion object
}

suspend fun <ID> S2ClientBuilder.Companion.get(
	protocol: Protocol,
	host: String,
	port: Int,
	path: String? = null
): S2AggregateKtorClient<ID> {
	val cqrsClient = when(protocol) {
		is  HTTP-> httpClientBuilder().cqrsClient("http", host, port, path)
		is  HTTPS-> httpClientBuilder().cqrsClient("https", host, port, path)
		is  TCP-> rsocketClientBuilder().cqrsClient("tcp", host, 7000, path)
		is  WS-> rsocketClientBuilder().cqrsClient("ws", host, 7000, path, secure = false)
		is  WSS-> rsocketClientBuilder().cqrsClient("wss", host, 7000, path, secure = true)
	}
	return S2AggregateKtorClient(cqrsClient)
}

