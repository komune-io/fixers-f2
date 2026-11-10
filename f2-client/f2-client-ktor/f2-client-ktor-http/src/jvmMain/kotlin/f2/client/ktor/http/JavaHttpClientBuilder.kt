package f2.client.ktor.http

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.engine.java.JavaHttpConfig

/**
 * [HttpClientBuilder] on the Ktor Java engine.
 *
 * @param config Additional configuration for the HTTP client.
 */
internal class JavaHttpClientBuilder(
    private val config: F2ClientConfigLambda<JavaHttpConfig>?
) : HttpClientBuilder {

    @Suppress("MissingUseCall") // a builder: the HttpF2Client it returns owns the HttpClient, its caller closes it
    override fun build(urlBase: String): HttpF2Client = HttpF2Client(
        httpClient = HttpClient(Java) { applyConfig(config) },
        urlBase = urlBase
    )
}
