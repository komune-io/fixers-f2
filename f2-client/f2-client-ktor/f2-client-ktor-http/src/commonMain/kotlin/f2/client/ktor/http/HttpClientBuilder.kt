package f2.client.ktor.http

import kotlinx.serialization.json.Json

expect fun httpClientBuilder(): HttpClientBuilder

expect class HttpClientBuilder
