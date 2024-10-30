package f2.client.ktor.http

import f2.client.F2Client
import io.ktor.client.HttpClient

expect open class HttpF2Client : F2Client {
    val httpClient: HttpClient
    val urlBase: String
}
