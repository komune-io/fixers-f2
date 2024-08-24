package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.httpClientBuilderDefault
import f2.client.ktor.http.httpClientBuilderGenerics
import f2.client.ktor.rsocket.builder.rSocketF2ClientBuilderDefault
import f2.client.ktor.rsocket.builder.rSocketF2ClientBuilderGenerics

/**
 * Builder object for creating instances of [F2Client] based on different protocols.
 */
object F2ClientBuilder {

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
        return when {
            url.startsWith("http:") -> httpClientBuilderDefault().build(url)
            url.startsWith("https:") -> httpClientBuilderDefault().build(url)
            else -> throw IllegalArgumentException(
                "Invalid Url[${url}] must start by one of http:, https:, tcp:, ws:, wss:"
            )
        }
    }

    /**
     * Creates an [F2Client] for various protocols including HTTP, HTTPS, TCP, WS, and WSS.
     *
     * @param urlBase The base URL to connect to. Must start with "http:", "https:", "tcp:", "ws:", or "wss:".
     * @param config Additional configuration for the client. Defaults to null.
     * @return An instance of [F2Client].
     * @throws InvalidUrlException if the URL does not start with a valid protocol.
     */
    suspend fun get(
        urlBase: String,
        config: F2ClientConfigLambda<*>? = null
    ): F2Client {
        return when {
            urlBase.startsWith("http:") -> httpClientBuilderGenerics(config).build(urlBase)
            urlBase.startsWith("https:") -> httpClientBuilderGenerics(config).build(urlBase)
            urlBase.startsWith("tcp:") -> rSocketF2ClientBuilderGenerics(config).build(urlBase, false)
            urlBase.startsWith("ws:") -> rSocketF2ClientBuilderGenerics(config).build(urlBase, false)
            urlBase.startsWith("wss:") -> rSocketF2ClientBuilderGenerics(config).build(urlBase, true)
            else -> throw InvalidUrlException(urlBase)
        }
    }

    /**
     * Creates an [F2Client] for various protocols including HTTP, HTTPS, TCP, WS, and WSS.
     *
     * @param url The URL to connect to. Must start with "http:", "https:", "tcp:", "ws:", or "wss:".
     * @return An instance of [F2Client].
     * @throws IllegalArgumentException if the URL does not start with a valid protocol.
     */
    suspend fun get(
        url: String,
    ): F2Client {
        return when {
            url.startsWith("http:") -> httpClientBuilderDefault().build(url)
            url.startsWith("https:") -> httpClientBuilderDefault().build(url)
            url.startsWith("tcp:") -> rSocketF2ClientBuilderDefault().build(url, false)
            url.startsWith("ws:") -> rSocketF2ClientBuilderDefault().build(url, false)
            url.startsWith("wss:") -> rSocketF2ClientBuilderDefault().build(url, false)
            else -> throw IllegalArgumentException(
                "Invalid Url[${url}] must start by one of http:, https:, tcp:, ws:, wss:"
            )
        }
    }
}
