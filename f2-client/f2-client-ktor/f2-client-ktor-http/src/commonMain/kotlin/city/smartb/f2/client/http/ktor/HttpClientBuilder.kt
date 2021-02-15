package city.smartb.f2.client.http.ktor

import f2.client.F2Client


expect fun httpClientBuilder(): HttpClientBuilder

expect class HttpClientBuilder {
	fun build(
		scheme: String,
		host: String ,
		port: Int,
		path: String? = null
	): F2Client
}
