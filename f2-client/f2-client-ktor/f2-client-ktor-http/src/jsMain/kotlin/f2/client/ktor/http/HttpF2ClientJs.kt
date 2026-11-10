package f2.client.ktor.http

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

// The client's functions as JS calls them, with the JS values it holds: a message is written with
// JSON.stringify, a result read with JSON.parse — no Kotlin serializer stands between. They are top-level
// functions: a member would shadow the reified Kotlin extensions of the same name on every platform.
// Call their result with invokeF2Supplier, invokeF2Function and invokeF2Consumer.

/** The supplier at [route] of the F2 server [client] calls: one GET, its results as JS values. */
@JsExport
fun <RESPONSE> httpF2Supplier(client: HttpF2Client, route: String): F2Supplier<RESPONSE> = F2Supplier {
    flow { emitResults(client.checked(send { client.httpClient.get(client.routeUrl(route)) })) }
}

/** The function at [route] of the F2 server [client] calls: one POST of every message, its results as JS values. */
@JsExport
fun <QUERY, RESPONSE> httpF2Function(client: HttpF2Client, route: String): F2Function<QUERY, RESPONSE> =
    F2Function { messages -> flow { emitResults(client.checked(client.postAll(route, messages))) } }

/** The consumer at [route] of the F2 server [client] calls: one POST of every message. */
@JsExport
fun <QUERY> httpF2Consumer(client: HttpF2Client, route: String): F2Consumer<QUERY> = F2Consumer { messages ->
    val _ = client.checked(client.postAll(route, messages))
}

private fun HttpF2Client.routeUrl(route: String) = "$urlBase/$route"

private suspend fun <MSG> HttpF2Client.postAll(route: String, messages: Flow<MSG>): HttpResponse {
    val body = JSON.stringify(messages.toList().toTypedArray())
    return send {
        httpClient.post(routeUrl(route)) { setBody(TextContent(body, ContentType.Application.Json)) }
    }
}

private suspend fun HttpF2Client.checked(response: HttpResponse): HttpResponse {
    if (!response.status.isSuccess()) throw json.responseError(response)
    return response
}

private suspend fun <RESPONSE> FlowCollector<RESPONSE>.emitResults(response: HttpResponse) {
    val text = response.bodyAsText()
    if (text.isBlank()) return
    val parsed: dynamic = JSON.parse(text)
    if (js("Array").isArray(parsed) as Boolean) {
        parsed.unsafeCast<Array<RESPONSE>>().forEach { emit(it) }
    } else {
        emit(parsed.unsafeCast<RESPONSE>())
    }
}
