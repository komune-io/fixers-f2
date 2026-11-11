package f2.client.ktor.http

import f2.dsl.cqrs.error.F2ErrorDTO
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException
import kotlin.js.Promise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
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
    fun addEventListener(type: String, listener: () -> Unit, options: dynamic)
    fun removeEventListener(type: String, listener: () -> Unit)
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

    /** The body the server answered, for [HttpF2JsFailureKind.HTTP] and [HttpF2JsFailureKind.UNREADABLE]. */
    val body: String?
}

/** What failed. */
@JsExport
enum class HttpF2JsFailureKind {
    /** An error status whose body is a JSON error object: [HttpF2JsFailure.error] is that F2 error. */
    F2,

    /** An error status whose body is text or empty: [HttpF2JsFailure.body] is that text. */
    HTTP,

    /** No response, or none read whole: the server is unreachable, or the connection broke. */
    NETWORK,

    /** A success status whose body is no JSON. */
    UNREADABLE,

    /** The client was closed before the call ended. */
    CLOSED,
}

/**
 * The F2 server at a URL, as JS calls it: each function is an object whose `invoke` takes an array of JSON
 * messages and an optional `AbortSignal`, and returns a promise of the JSON results, no Kotlin value in between. A
 * message is written with JSON.stringify and a result read with JSON.parse, so the messages are an array of plain
 * JSON: an array, without cycle, of plain objects, arrays and primitives; anything else rejects with a `TypeError`
 * before any request. A failed call rejects with an `HttpF2JsFailure`; a call aborted by its signal with the
 * signal's reason, as `fetch` does. Every call settles once.
 */
@JsExport
class HttpF2JsClient internal constructor(internal val client: HttpF2Client) {
    private val scope = CoroutineScope(SupervisorJob())
    private var isClosed = false

    /** The function at [route]: one POST of every message, its results. */
    fun <QUERY, RESPONSE> function(route: String): F2JsFunction<QUERY, RESPONSE> = F2JsFunction(this, route)

    /** The supplier at [route]: one GET, its results. */
    fun <RESPONSE> supplier(route: String): F2JsSupplier<RESPONSE> = F2JsSupplier(this, route)

    /** The consumer at [route]: one POST of every message. */
    fun <QUERY> consumer(route: String): F2JsConsumer<QUERY> = F2JsConsumer(this, route)

    /** Ends every pending call, which rejects as `CLOSED`, and releases the connections; later calls reject alike. */
    fun close() {
        isClosed = true
        scope.cancel()
        client.httpClient.close()
    }

    // Settles once, whatever ends the job: its result, a prepared rejection, any other failure, a cancellation.
    internal fun <T> call(signal: AbortSignal?, block: suspend () -> T): Promise<T> = Promise { resolve, reject ->
        val settle = Settle(resolve, reject)
        when {
            signal?.aborted == true -> {
                settle.reject(signal.reason)
            }
            isClosed -> {
                settle.reject(closed())
            }
            else -> {
                val job = scope.launch { settle.resolveWith(block) }
                val abort = { job.cancel() }
                signal?.addEventListener("abort", abort, js("({ once: true })"))
                job.invokeOnCompletion { cause ->
                    signal?.removeEventListener("abort", abort)
                    when {
                        cause == null -> Unit
                        cause !is CancellationException -> settle.reject(unexpected(cause))
                        signal?.aborted == true -> settle.reject(signal.reason)
                        isClosed -> settle.reject(closed())
                        else -> settle.reject(failure(HttpF2JsFailureKind.NETWORK, "The call was cancelled"))
                    }
                }
            }
        }
    }

    private fun closed(): Throwable = failure(HttpF2JsFailureKind.CLOSED, "The client is closed")
}

/** An F2 function of the server: `invoke(msgs, signal?)`. */
@JsExport
class F2JsFunction<QUERY, RESPONSE> internal constructor(private val owner: HttpF2JsClient, private val route: String) {
    /** One POST of every message, its results; [signal] aborts the call. */
    fun invoke(msgs: Array<JsonOf<QUERY>>, signal: AbortSignal? = null): Promise<Array<JsonOf<RESPONSE>>> =
        owner.call(signal) { owner.client.results(owner.client.checked(owner.client.postAll(route, msgs))) }
}

/** An F2 supplier of the server: `invoke(signal?)`. */
@JsExport
class F2JsSupplier<RESPONSE> internal constructor(private val owner: HttpF2JsClient, private val route: String) {
    /** One GET, its results; [signal] aborts the call. */
    fun invoke(signal: AbortSignal? = null): Promise<Array<JsonOf<RESPONSE>>> = owner.call(signal) {
        val client = owner.client
        client.results(client.checked(client.request { client.httpClient.get("${client.urlBase}/$route") }))
    }
}

/** An F2 consumer of the server: `invoke(msgs, signal?)`. */
@JsExport
class F2JsConsumer<QUERY> internal constructor(private val owner: HttpF2JsClient, private val route: String) {
    /** One POST of every message; [signal] aborts the call. */
    fun invoke(msgs: Array<JsonOf<QUERY>>, signal: AbortSignal? = null): Promise<Unit> =
        // A promise of Unit would resolve to Kotlin's Unit object, where a `Promise<void>` holds undefined.
        owner.call<dynamic>(signal) {
            val _ = owner.client.checked(owner.client.postAll(route, msgs))
            undefined
        }.unsafeCast<Promise<Unit>>()
}

/** The F2 server at [urlBase], called on the platform's fetch. */
@JsExport
fun httpF2JsClient(urlBase: String): HttpF2JsClient = HttpF2JsClient(httpClientBuilderDefault().build(urlBase))
