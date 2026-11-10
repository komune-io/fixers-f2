package f2.client.ktor.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandler
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val mockJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}

fun HttpClientConfig<*>.mockContentNegotiation() {
    install(ContentNegotiation) { json(mockJson) }
}

@Suppress("MissingUseCall") // a factory: the HttpF2Client it returns owns the HttpClient, the calling test holds it
fun mockF2Client(
    urlBase: String = "http://localhost",
    config: HttpClientConfig<*>.() -> Unit = { mockContentNegotiation() },
    handler: MockRequestHandler,
): HttpF2Client = HttpF2Client(HttpClient(MockEngine(handler), config), urlBase, mockJson)
