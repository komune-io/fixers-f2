package f2.client.ktor.http

import io.ktor.client.engine.cio.CIOEngineConfig
import kotlinx.serialization.json.Json

actual fun httpClientBuilderDefault(): HttpClientBuilder = HttpClientBuilder()

actual fun httpClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
): HttpClientBuilder = HttpClientBuilder(json)


fun httpClientBuilder(
    json: Json? = F2DefaultJson,
    config: F2ClientConfigLambda<CIOEngineConfig>? = {  }
) = HttpClientBuilder(json, config)
