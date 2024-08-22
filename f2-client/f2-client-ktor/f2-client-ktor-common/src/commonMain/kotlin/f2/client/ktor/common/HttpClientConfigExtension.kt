package f2.client.ktor.common

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.applyConfig(
    json: Json? = null,
    config: F2ClientConfigLambda<T>? = null
) {
    json?.let {
        install(ContentNegotiation) {
            json(json)
        }
    }

    config?.let { it(this) }
}


