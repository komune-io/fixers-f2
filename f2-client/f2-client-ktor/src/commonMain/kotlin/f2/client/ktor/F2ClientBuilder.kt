package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.httpClientBuilderDefault
import f2.client.ktor.http.httpClientBuilderGenerics
import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * Builder object for creating instances of [F2Client] based on different protocols.
 */
@JsExport
object F2ClientBuilder {

    private const val HTTP_PREFIX = "http:"
    private const val HTTPS_PREFIX = "https:"

    internal fun isHttpUrl(url: String): Boolean {
        return url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)
    }

    private fun requireHttpUrl(url: String) {
        if (!isHttpUrl(url)) {
            throw IllegalArgumentException("Invalid Url[${url}] must start with one of http:, https:")
        }
    }

    /**
     * Creates an [F2Client] for HTTP or HTTPS communication.
     *
     * @param url The URL to connect to. Must start with "http:" or "https:".
     * @return An instance of [F2Client].
     * @throws IllegalArgumentException if the URL does not start with "http:" or "https:".
     */
    fun getHttp(
        url: String,
    ): F2Client {
        requireHttpUrl(url)
        return httpClientBuilderDefault().build(url)
    }

    /**
     * Creates an [F2Client] for HTTP or HTTPS communication, with optional configuration.
     *
     * @param urlBase The base URL to connect to. Must start with "http:" or "https:".
     * @param config Additional configuration for the client. Defaults to null.
     * @return An instance of [F2Client].
     * @throws InvalidUrlException if the URL does not start with a valid protocol.
     */
    @JsName("getWithConfig")
    suspend fun get(
        urlBase: String,
        config: F2ClientConfigLambda<*>? = null
    ): F2Client {
        if (!isHttpUrl(urlBase)) {
            throw InvalidUrlException(urlBase)
        }
        return httpClientBuilderGenerics(config).build(urlBase)
    }

    /**
     * Creates an [F2Client] for HTTP or HTTPS communication.
     *
     * @param url The URL to connect to. Must start with "http:" or "https:".
     * @return An instance of [F2Client].
     * @throws IllegalArgumentException if the URL does not start with "http:" or "https:".
     */
    suspend fun get(
        url: String,
    ): F2Client {
        requireHttpUrl(url)
        return httpClientBuilderDefault().build(url)
    }
}
