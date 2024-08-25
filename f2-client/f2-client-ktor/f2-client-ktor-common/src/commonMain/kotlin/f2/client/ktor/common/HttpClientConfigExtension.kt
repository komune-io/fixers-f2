package f2.client.ktor.common

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.applyConfig(
    config: F2ClientConfigLambda<T>? = null
) {
    install(ContentNegotiation) {
        json(F2DefaultJson)
    }

    config?.let { it(this) }
}


