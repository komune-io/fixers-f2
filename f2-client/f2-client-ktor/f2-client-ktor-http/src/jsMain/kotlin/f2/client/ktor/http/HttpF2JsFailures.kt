package f2.client.ktor.http

import f2.dsl.cqrs.error.F2ErrorDTO
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.io.IOException

// The JS values a call of HttpF2JsClient settles its promise with.

/** A failure prepared as the JS value the caller receives. */
internal class Rejection(val value: dynamic, cause: Throwable? = null) : Exception(cause)

/** A promise's two ends, used once: the first outcome wins and the others are dropped. */
internal class Settle<T>(private val onResolve: (T) -> Unit, private val onReject: (Throwable) -> Unit) {
    private var isSettled = false

    fun reject(reason: dynamic) {
        if (isSettled) return
        isSettled = true
        onReject(reason.unsafeCast<Throwable>())
    }

    // Any failure of the block settles the promise; only a cancellation is left to the job's completion.
    @Suppress("TooGenericExceptionCaught") // a defect of the client must reject the promise, not leave it pending
    suspend fun resolveWith(block: suspend () -> T) {
        try {
            val value = block()
            if (!isSettled) {
                isSettled = true
                onResolve(value)
            }
        } catch (e: Rejection) {
            reject(e.value)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            reject(unexpected(e))
        }
    }
}

/** An I/O failure is the network's; anything else, a defect of the client, rejects with a plain `Error`. */
internal fun unexpected(cause: Throwable): Throwable = if (cause is IOException) {
    failure(HttpF2JsFailureKind.NETWORK, cause.message ?: "The connection broke")
} else {
    js("Error")(cause.message ?: cause.toString()).unsafeCast<Throwable>()
}

internal fun failure(
    kind: HttpF2JsFailureKind,
    message: String,
    status: Int? = null,
    error: F2ErrorDTO? = null,
    body: String? = null,
): Throwable {
    val value: dynamic = js("new Error()")
    value.message = message
    value.kind = kind.name
    value.status = status
    value.error = error?.toJson()
    value.body = body
    return value.unsafeCast<Throwable>()
}

internal fun typeError(message: String): Throwable = js("TypeError")(message).unsafeCast<Throwable>()

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

