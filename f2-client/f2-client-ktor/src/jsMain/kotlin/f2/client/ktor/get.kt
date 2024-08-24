package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.httpClientBuilder
import f2.client.ktor.rsocket.builder.rSocketClientBuilder
import io.ktor.client.engine.HttpClientEngineConfig

suspend fun F2ClientBuilder.get(
    urlBase: String,
    config: F2ClientConfigLambda<HttpClientEngineConfig>? = {}
): F2Client {
	return when {
		urlBase.startsWith("http:") -> httpClientBuilder(config).build(urlBase)
		urlBase.startsWith("https:") -> httpClientBuilder(config).build(urlBase)
		urlBase.startsWith("tcp:") -> rSocketClientBuilder(config).build(urlBase, false)
		urlBase.startsWith("ws:") -> rSocketClientBuilder(config).build(urlBase, false)
		urlBase.startsWith("wss:") -> rSocketClientBuilder(config).build(urlBase, true)
		else -> throw InvalidUrlException(urlBase)
	}
}
