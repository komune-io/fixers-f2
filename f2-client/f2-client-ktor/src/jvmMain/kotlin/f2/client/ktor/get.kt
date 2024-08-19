package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.http.F2ClientConfigLambda
import f2.client.ktor.http.F2DefaultJson
import f2.client.ktor.http.httpClientBuilder
import f2.client.ktor.rsocket.rSocketF2ClientBuilderDefault
import io.ktor.client.engine.cio.CIOEngineConfig
import kotlinx.serialization.json.Json

suspend fun F2ClientBuilder.get(
	urlBase: String,
	json: Json? = F2DefaultJson,
	config: F2ClientConfigLambda<CIOEngineConfig>? = null
): F2Client {
	return when {
		urlBase.startsWith("http:") -> httpClientBuilder(json, config).build(urlBase)
		urlBase.startsWith("https:") -> httpClientBuilder(json, config).build(urlBase)
		urlBase.startsWith("tcp:") -> rSocketF2ClientBuilderDefault().build(urlBase, false)
		urlBase.startsWith("ws:") -> rSocketF2ClientBuilderDefault().build(urlBase, false)
		urlBase.startsWith("wss:") -> rSocketF2ClientBuilderDefault().build(urlBase, true)
		else -> throw InvalidUrlException(urlBase)
	}
}
