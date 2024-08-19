package f2.client.ktor.http

import io.ktor.client.HttpClientConfig


typealias F2ClientConfigLambda<T> = HttpClientConfig<T>.() -> Unit
