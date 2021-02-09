package city.smartb.f2.client.http.ktor

import city.smartb.f2.dsl.cqrs.S2CQRSClient


expect fun httpClientBuilder(): HttpClientBuilder

expect class HttpClientBuilder {
	fun cqrsClient(
		scheme: String,
		host: String ,
		port: Int,
		path: String? = null
	): S2CQRSClient
}
