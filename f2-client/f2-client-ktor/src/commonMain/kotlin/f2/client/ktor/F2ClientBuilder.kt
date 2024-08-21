package f2.client.ktor

import f2.client.F2Client
import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.http.F2DefaultJson
import f2.client.ktor.http.httpClientBuilderDefault
import f2.client.ktor.http.httpClientBuilderGenerics
import f2.client.ktor.rsocket.builder.rSocketF2ClientBuilderDefault
import kotlinx.serialization.json.Json

object F2ClientBuilder

fun F2ClientBuilder.getHttp(
    url: String,
): F2Client {
    return when {
        url.startsWith("http:") -> httpClientBuilderDefault().build(url)
        url.startsWith("https:") -> httpClientBuilderDefault().build(url)
        else -> throw IllegalArgumentException("Invalid Url[${url}] must start by one of http:, https:, tcp: ws: wss:")
    }
}

suspend fun F2ClientBuilder.get(
    urlBase: String,
    json: Json? = F2DefaultJson,
    config: F2ClientConfigLambda<*>? = null
): F2Client {
    return when {
        urlBase.startsWith("http:") -> httpClientBuilderGenerics(json, config).build(urlBase)
        urlBase.startsWith("https:") -> httpClientBuilderGenerics(json, config).build(urlBase)
        //TODO CHANGE to rSocketF2ClientBuilderGenerics
        urlBase.startsWith("tcp:") -> rSocketF2ClientBuilderDefault().build(urlBase, false)
        urlBase.startsWith("ws:") -> rSocketF2ClientBuilderDefault().build(urlBase, false)
        urlBase.startsWith("wss:") -> rSocketF2ClientBuilderDefault().build(urlBase, true)
        else -> throw InvalidUrlException(urlBase)
    }
}


suspend fun F2ClientBuilder.get(
    url: String,
): F2Client {
    return when {
        url.startsWith("http:") -> httpClientBuilderDefault().build(url)
        url.startsWith("https:") -> httpClientBuilderDefault().build(url)
        url.startsWith("tcp:") -> rSocketF2ClientBuilderDefault().build(url, false)
        url.startsWith("ws:") -> rSocketF2ClientBuilderDefault().build(url, false)
        url.startsWith("wss:") -> rSocketF2ClientBuilderDefault().build(url, false)
        else -> throw IllegalArgumentException("Invalid Url[${url}] must start by one of http:, https:, tcp: ws: wss:")
    }
}

