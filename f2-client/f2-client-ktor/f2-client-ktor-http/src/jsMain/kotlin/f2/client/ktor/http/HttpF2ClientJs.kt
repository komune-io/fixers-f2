package f2.client.ktor.http

import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import kotlin.js.Promise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.promise

/**
 * The JSON of a [T]: what a function sends and answers on the wire. Declared by the `Json<T>` type at the head
 * of every TypeScript contract, never by Kotlin, which only names it.
 */
external interface Json<T>

/**
 * The F2 server at a URL, as JS calls it: each function is a JS function of an array of JSON messages to a
 * promise of the JSON results, no Kotlin value in between. A message is written with JSON.stringify and a result
 * read with JSON.parse, so a message is a plain object or array; an instance of an exported Kotlin class is
 * refused, its fields being mangled. A refused call rejects with the `F2Exception` the server answered.
 */
@JsExport
class HttpF2JsClient internal constructor(private val client: HttpF2Client) {
    private val scope = CoroutineScope(SupervisorJob())

    /** The function at [route]: one POST of every message, its results. */
    fun <QUERY, RESPONSE> function(route: String): (msgs: Array<Json<QUERY>>) -> Promise<Array<Json<RESPONSE>>> =
        { msgs -> scope.promise { client.checked(client.postAll(route, msgs)).results() } }

    /** The supplier at [route]: one GET, its results. */
    fun <RESPONSE> supplier(route: String): () -> Promise<Array<Json<RESPONSE>>> =
        { scope.promise { client.checked(send { client.httpClient.get(client.routeUrl(route)) }).results() } }

    /** The consumer at [route]: one POST of every message. */
    fun <QUERY> consumer(route: String): (msgs: Array<Json<QUERY>>) -> Promise<Unit> =
        { msgs ->
            // A promise of Unit would resolve to Kotlin's Unit object, where a `Promise<void>` holds undefined.
            scope.promise<dynamic> {
                val _ = client.checked(client.postAll(route, msgs))
                undefined
            }.unsafeCast<Promise<Unit>>()
        }
}

/** The F2 server at [urlBase], called on the platform's fetch. */
@JsExport
fun httpF2JsClient(urlBase: String): HttpF2JsClient = HttpF2JsClient(httpClientBuilderDefault().build(urlBase))

private fun HttpF2Client.routeUrl(route: String) = "$urlBase/$route"

private suspend fun HttpF2Client.postAll(route: String, messages: Array<*>): HttpResponse {
    messages.forEach { message ->
        require(isPlainJsValue(message)) {
            "A JS message is a plain object or array, not an instance of an exported Kotlin class: $route"
        }
    }
    val body = JSON.stringify(messages)
    return send {
        httpClient.post(routeUrl(route)) { setBody(TextContent(body, ContentType.Application.Json)) }
    }
}

private fun isPlainJsValue(value: Any?): Boolean {
    if (value == null || jsTypeOf(value) != "object") return true
    val prototype: dynamic = js("Object").getPrototypeOf(value)
    return js("Array").isArray(value) as Boolean || prototype == js("Object.prototype") || prototype == null
}

private suspend fun HttpF2Client.checked(response: HttpResponse): HttpResponse {
    if (!response.status.isSuccess()) throw json.responseError(response)
    return response
}

// A body is the array of the results, or a single result.
private suspend fun <RESPONSE> HttpResponse.results(): Array<Json<RESPONSE>> {
    val text = bodyAsText()
    if (text.isBlank()) return emptyArray()
    val parsed: dynamic = JSON.parse(text)
    return if (js("Array").isArray(parsed) as Boolean) parsed.unsafeCast<Array<Json<RESPONSE>>>() else arrayOf(parsed)
}
