package f2.client.ktor.common

import io.ktor.client.HttpClientConfig

typealias F2ClientConfigLambda<T> = HttpClientConfig<T>.() -> Unit

