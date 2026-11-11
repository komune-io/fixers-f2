package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.httpClientBuilderGenerics

/**
 * Builds the [F2Client] of an F2 server from its URL. From JS, `httpClientBuilderDefault().build(url)`.
 */
object F2ClientBuilder {

    private const val HTTP_PREFIX = "http:"
    private const val HTTPS_PREFIX = "https:"

    /**
     * The HTTP client of the F2 server at [urlBase], its Ktor client configured by [config].
     *
     * @throws InvalidUrlException when [urlBase] starts with neither `http:` nor `https:`.
     */
    fun get(
        urlBase: String,
        config: F2ClientConfigLambda<*>? = null
    ): F2Client {
        if (!urlBase.startsWith(HTTP_PREFIX) && !urlBase.startsWith(HTTPS_PREFIX)) {
            throw InvalidUrlException(urlBase)
        }
        return httpClientBuilderGenerics(config).build(urlBase)
    }
}
