package f2.client.ktor.http

expect fun httpClientBuilder(): HttpClientBuilder

expect class HttpClientBuilder {

    fun build(urlBase: String): HttpF2Client

}
