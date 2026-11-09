package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.httpClientBuilder
import io.ktor.client.engine.java.JavaHttpConfig

fun F2ClientBuilder.get(
    urlBase: String,
    config: F2ClientConfigLambda<JavaHttpConfig>? = null
): F2Client = buildHttpClient(urlBase, config)

@Deprecated(SUSPEND_GET_REMOVED, level = DeprecationLevel.HIDDEN)
@Suppress("RedundantSuspendModifier") // the suspend signature is what compiled callers link to
suspend fun F2ClientBuilder.get(
    urlBase: String,
    config: F2ClientConfigLambda<JavaHttpConfig>? = null
): F2Client = buildHttpClient(urlBase, config)

private fun F2ClientBuilder.buildHttpClient(
    urlBase: String,
    config: F2ClientConfigLambda<JavaHttpConfig>?
): F2Client {
    if (!isHttpUrl(urlBase)) {
        throw InvalidUrlException(urlBase)
    }
    return httpClientBuilder(config).build(urlBase)
}
