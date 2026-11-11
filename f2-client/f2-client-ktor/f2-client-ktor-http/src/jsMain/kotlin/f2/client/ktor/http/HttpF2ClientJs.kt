package f2.client.ktor.http

import f2.dsl.cqrs.error.F2ErrorDTO
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import kotlin.coroutines.cancellation.CancellationException
import kotlin.js.Promise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * The JSON of a [T]: what a function sends and answers on the wire. Declared by the `Json<T>` type at the head
 * of every TypeScript contract, never by Kotlin, which only names it.
 */
@JsName("Json")
external interface JsonOf<T>

/** The platform's `AbortSignal` (the DOM's, Node's), what a call reads of it. */
external interface AbortSignal {
    val aborted: Boolean
    val reason: dynamic
    fun addEventListener(type: String, listener: () -> Unit)
}

/**
 * Why a call of [HttpF2JsClient] failed. Its promise rejects with a JS `Error` carrying these fields, read as
 * `Json<HttpF2JsFailure>`: no Kotlin object reaches the caller.
 */
@JsExport
interface HttpF2JsFailure {
    val kind: HttpF2JsFailureKind
    val message: String

    /** The HTTP status of the response, or `null` when none came back. */
    val status: Int?

    /** The error the server answered, for [HttpF2JsFailureKind.F2] only. */
    val error: F2ErrorDTO?
}

/** What failed. */
@JsExport
enum class HttpF2JsFailureKind {
    /** The server answered an error status; [HttpF2JsFailure.error] is its F2 error, a body that is none included. */
    F2,

    /** No response came back: the server is unreachable, or the connection broke. */
    NETWORK,

    /** A success status whose body is no JSON. */
    UNREADABLE,

    /** The client was closed before the call ended. */
    CLOSED,
}

/**
 * The F2 server at a URL, as JS calls it: each function is a JS function of an array of JSON messages to a
 * promise of the JSON results, no Kotlin value in between. A message is written with JSON.stringify and a result
 * read with JSON.parse, so a message is plain JSON: an array of plain objects, arrays and primitives; anything
 * else (an instance of an exported Kotlin class, at any depth) rejects with a `TypeError` before any request. A
 * failed call rejects with an `HttpF2JsFailure`; an aborted one with its signal's reason, as `fetch` does.
 */
@JsExport
class HttpF2JsClient internal constructor(private val client: HttpF2Client) {
    private val scope = CoroutineScope(SupervisorJob())

    /** The function at [route]: one POST of every message, its results. [signal] aborts each call. */
    fun <QUERY, RESPONSE> function(
        route: String,
        signal: AbortSignal? = null,
    ): (msgs: Array<JsonOf<QUERY>>) -> Promise<Array<JsonOf<RESPONSE>>> =
        { msgs -> call(signal) { client.checked(client.postAll(route, msgs)).results() } }

    /** The supplier at [route]: one GET, its results. [signal] aborts each call. */
    fun <RESPONSE> supplier(route: String, signal: AbortSignal? = null): () -> Promise<Array<JsonOf<RESPONSE>>> =
        {
            call(signal) {
                client.checked(client.request { client.httpClient.get("${client.urlBase}/$route") }).results()
            }
        }

    /** The consumer at [route]: one POST of every message. [signal] aborts each call. */
    fun <QUERY> consumer(route: String, signal: AbortSignal? = null): (msgs: Array<JsonOf<QUERY>>) -> Promise<Unit> =
        { msgs ->
            // A promise of Unit would resolve to Kotlin's Unit object, where a `Promise<void>` holds undefined.
            call<dynamic>(signal) {
                val _ = client.checked(client.postAll(route, msgs))
                undefined
            }.unsafeCast<Promise<Unit>>()
        }

    /** Ends every pending call, which rejects as `CLOSED`, and releases the connections; later calls reject alike. */
    fun close() {
        scope.cancel()
        client.httpClient.close()
    }

    private fun closed(): Throwable = failure(HttpF2JsFailureKind.CLOSED, "The client is closed")

    private fun <T> call(signal: AbortSignal?, block: suspend () -> T): Promise<T> = Promise { resolve, reject ->
        if (signal?.aborted == true) {
            reject(signal.reason.unsafeCast<Throwable>())
        } else if (!scope.isActive) {
            reject(closed())
        } else {
            val job = scope.launch {
                try {
                    resolve(block())
                } catch (e: Rejection) {
                    reject(e.value.unsafeCast<Throwable>())
                }
            }
            job.invokeOnCompletion { cause ->
                if (cause is CancellationException) {
                    reject(signal?.takeIf { it.aborted }?.reason?.unsafeCast<Throwable>() ?: closed())
                }
            }
            signal?.addEventListener("abort") { job.cancel() }
        }
    }
}

/** The F2 server at [urlBase], called on the platform's fetch. */
@JsExport
fun httpF2JsClient(urlBase: String): HttpF2JsClient = HttpF2JsClient(httpClientBuilderDefault().build(urlBase))

// A failure the promise rejects with, prepared as the JS value the caller receives.
private class Rejection(val value: dynamic, cause: Throwable? = null) : Exception(cause)

private fun failure(
    kind: HttpF2JsFailureKind,
    message: String,
    status: Int? = null,
    error: F2ErrorDTO? = null,
): Throwable {
    val value: dynamic = js("new Error()")
    value.message = message
    value.kind = kind.name
    value.status = status
    value.error = error?.toJson()
    return value.unsafeCast<Throwable>()
}

private fun typeError(message: String): Throwable = js("TypeError")(message).unsafeCast<Throwable>()

// The JSON of an F2 error, as its body reads: `refusal.parameters` an object, `refusal.citers` an array.
private fun F2ErrorDTO.toJson(): dynamic {
    val value: dynamic = js("({})")
    value.message = message
    value.id = id
    value.timestamp = timestamp
    value.code = code
    value.requestId = requestId
    value.refusal = refusal?.let { refusal ->
        val parameters: dynamic = js("({})")
        refusal.parameters.forEach { (key, parameter) -> parameters[key] = parameter }
        val json: dynamic = js("({})")
        json.code = refusal.code
        json.parameters = parameters
        json.citers = refusal.citers.toTypedArray()
        json
    }
    return value
}

// Any failure to get a response is the network's: the request never got an answer to read.
@Suppress("TooGenericExceptionCaught") // a JS engine fails a fetch with any JS error, a TypeError most often
private suspend fun HttpF2Client.request(block: suspend () -> HttpResponse): HttpResponse = try {
    send(block)
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    throw Rejection(failure(HttpF2JsFailureKind.NETWORK, e.message ?: "No response from $urlBase"), e)
}

private suspend fun HttpF2Client.postAll(route: String, messages: dynamic): HttpResponse {
    if (!isArray(messages)) {
        throw Rejection(typeError("The messages of $route are an array"))
    }
    if (!isPlainJson(messages)) {
        throw Rejection(typeError("A message of $route is plain JSON: no instance of a class, at any depth"))
    }
    val body = JSON.stringify(messages)
    return request { httpClient.post("$urlBase/$route") { setBody(TextContent(body, ContentType.Application.Json)) } }
}

// Plain JSON at every depth: primitives, arrays, and objects whose prototype is Object's or none.
private fun isPlainJson(value: dynamic): Boolean = when (jsTypeOf(value)) {
    "string", "number", "boolean", "undefined" -> {
        true
    }
    "object" -> {
        isPlainJsonObject(value)
    }
    else -> {
        false
    }
}

private fun isPlainJsonObject(value: dynamic): Boolean = when {
    value == null -> {
        true
    }
    isArray(value) -> {
        value.unsafeCast<Array<dynamic>>().all { isPlainJson(it) }
    }
    else -> {
        val prototype: dynamic = js("Object").getPrototypeOf(value)
        (prototype == js("Object.prototype") || prototype == null) &&
            js("Object").values(value).unsafeCast<Array<dynamic>>().all { isPlainJson(it) }
    }
}

private fun isArray(value: dynamic): Boolean = js("Array").isArray(value) as Boolean

private suspend fun HttpF2Client.checked(response: HttpResponse): HttpResponse {
    if (!response.status.isSuccess()) {
        val error = json.responseError(response).error
        throw Rejection(failure(HttpF2JsFailureKind.F2, error.message, response.status.value, error))
    }
    return response
}

// A body is the array of the results, or a single result.
@Suppress("TooGenericExceptionCaught") // JSON.parse fails with a JS SyntaxError, which Kotlin sees as a Throwable
private suspend fun <RESPONSE> HttpResponse.results(): Array<JsonOf<RESPONSE>> {
    val text = bodyAsText()
    if (text.isBlank()) return emptyArray()
    val parsed: dynamic = try {
        JSON.parse(text)
    } catch (e: Throwable) {
        throw Rejection(failure(HttpF2JsFailureKind.UNREADABLE, e.message ?: "No JSON", status.value), e)
    }
    return if (isArray(parsed)) {
        parsed.unsafeCast<Array<JsonOf<RESPONSE>>>()
    } else {
        arrayOf(parsed)
    }
}
