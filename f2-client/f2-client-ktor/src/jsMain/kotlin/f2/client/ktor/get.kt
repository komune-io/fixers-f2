package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.httpClientBuilder
import io.ktor.client.engine.HttpClientEngineConfig

suspend fun F2ClientBuilder.get(
    urlBase: String,
    config: F2ClientConfigLambda<HttpClientEngineConfig>? = {}
): F2Client {
	return when {
		urlBase.startsWith("http:") -> httpClientBuilder(config).build(urlBase)
		urlBase.startsWith("https:") -> httpClientBuilder(config).build(urlBase)
		else -> throw InvalidUrlException(urlBase)
	}
}
