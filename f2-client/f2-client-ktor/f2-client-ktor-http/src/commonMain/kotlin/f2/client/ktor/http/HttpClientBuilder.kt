package f2.client.ktor.http

expect fun httpClientBuilder(): HttpClientBuilder

expect class HttpClientBuilder {
//	fun build(
//		scheme: String,
//		host: String ,
//		port: Int,
//		path: String? = null
//	): F2Client
}
