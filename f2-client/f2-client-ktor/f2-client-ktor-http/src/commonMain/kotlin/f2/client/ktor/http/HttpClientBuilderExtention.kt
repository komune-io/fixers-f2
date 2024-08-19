package f2.client.ktor.http

import kotlinx.serialization.json.Json

expect fun httpClientBuilderDefault(): HttpClientBuilder

expect fun httpClientBuilderGenerics(
    json: Json? = F2DefaultJson,
    config: F2ClientConfigLambda<*>? = null
): HttpClientBuilder
