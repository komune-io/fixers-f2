package f2.client.ktor.http

expect class HttpClientBuilder {
    fun build(urlBase: String): HttpF2Client
}
