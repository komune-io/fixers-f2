package f2.client.ktor.http

import io.ktor.client.engine.HttpClientEngineConfig
import kotlinx.serialization.json.Json

@JsExport
actual fun httpClientBuilderDefault() = HttpClientBuilder()

@JsExport
actual fun httpClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
) = HttpClientBuilder(json)

fun httpClientBuilder(
	json: Json? = F2DefaultJson,
	config: F2ClientConfigLambda<HttpClientEngineConfig>? = {  }
) = HttpClientBuilder(json)
