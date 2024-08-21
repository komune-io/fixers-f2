package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class RSocketClientBuilder(
	private val json: Json? = null,
	private val config: F2ClientConfigLambda<CIOEngineConfig>? = {}
) {
	actual fun build(): HttpClient = HttpClient(CIO) {
		withRSocket()
		json?.let {
			install(ContentNegotiation) {
				json(json)
			}
		}

		config?.let { it(this) }
	}
}


actual fun rsocketClientBuilderDefault(): RSocketClientBuilder {
	return f2.client.ktor.rsocket.builder.RSocketClientBuilder()
}

actual fun rsocketClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
): RSocketClientBuilder {
	return f2.client.ktor.rsocket.builder.RSocketClientBuilder(json, config)
}

