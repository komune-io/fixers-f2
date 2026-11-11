package f2.client.ktor.http

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import kotlin.coroutines.cancellation.CancellationException

// What a call of HttpF2JsClient does on the wire.

// Any failure to get a response, or to read it whole, is the network's.
@Suppress("TooGenericExceptionCaught") // a JS engine fails a fetch with any JS error, a TypeError most often
private suspend fun <T> HttpF2Client.network(block: suspend () -> T): T = try {
    block()
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    throw Rejection(failure(HttpF2JsFailureKind.NETWORK, e.message ?: "No response from $urlBase"), e)
}

internal suspend fun HttpF2Client.request(block: suspend () -> HttpResponse): HttpResponse = network { send(block) }

internal suspend fun HttpF2Client.postAll(
    route: String,
    messages: dynamic,
    headers: suspend () -> List<Pair<String, String>>,
): HttpResponse {
    if (!isArray(messages)) throw Rejection(typeError("The messages of $route are an array"))
    val checked = try {
        jsonCopy(messages, js("new Set()"))
    } catch (e: NotJson) {
        throw Rejection(typeError("A message of $route ${e.problem}"), e)
    }
    val body = JSON.stringify(checked)
    val sent = headers()
    return request {
        httpClient.post("$urlBase/$route") {
            withHeaders(sent)
            setBody(TextContent(body, ContentType.Application.Json))
        }
    }
}


internal val jsObject: dynamic = js("Object")

private class NotJson(val problem: String) : Exception(problem)

// The plain JSON copy of [value], each property read once: what is checked is what is sent, a getter
// answering twice included. Throws NotJson on a class instance at any depth, a cycle through [ancestors],
// or a number JSON.stringify would write as null.
private fun jsonCopy(value: dynamic, ancestors: dynamic): dynamic = when (jsTypeOf(value)) {
    "string", "boolean", "undefined" -> value
    "number" -> if (js("Number").isFinite(value) as Boolean) value else throw NotJson("holds $value, no finite number")
    "object" -> objectCopy(value, ancestors)
    else -> throw NotJson("is plain JSON, not a ${jsTypeOf(value)}")
}

private fun objectCopy(value: dynamic, ancestors: dynamic): dynamic {
    if (value == null) return null
    if (ancestors.has(value) as Boolean) throw NotJson("holds a cycle, which JSON cannot write")
    val isList = isArray(value)
    if (!isList && isClassInstance(value)) throw NotJson("is plain JSON: no instance of a class, at any depth")
    ancestors.add(value)
    // No prototype: a `__proto__` key is copied as the key it is, not set as the copy's prototype.
    val copy: dynamic = if (isList) js("Array")(value.length) else jsObject.create(null)
    jsObject.entries(value).unsafeCast<Array<Array<dynamic>>>().forEach { (key, child) ->
        copy[key] = jsonCopy(child, ancestors)
    }
    ancestors.delete(value)
    return copy
}

// An object whose prototype is neither Object's nor none: an instance of a class, a Kotlin one included.
internal fun isClassInstance(value: dynamic): Boolean {
    val prototype: dynamic = jsObject.getPrototypeOf(value)
    return prototype != js("Object.prototype") && prototype != null
}

internal fun isArray(value: dynamic): Boolean = js("Array").isArray(value) as Boolean

private suspend fun HttpResponse.text(client: HttpF2Client): String = client.network { bodyAsText() }

@Suppress("TooGenericExceptionCaught", "SwallowedException") // JSON.parse fails with a JS SyntaxError: no object
private fun isJsonObject(text: String): Boolean = try {
    // On a dynamic value, `let` and the like are JS method calls: the body is read through a typed local.
    val body: Any? = JSON.parse(text)
    body != null && jsTypeOf(body) == "object" && !isArray(body)
} catch (_: Throwable) {
    false
}

internal suspend fun HttpF2Client.checked(response: HttpResponse): HttpResponse {
    if (response.status.isSuccess()) return response
    val status = response.status.value
    val text = response.text(this)
    throw Rejection(
        if (isJsonObject(text)) {
            val error = network { json.responseError(response) }.error
            failure(HttpF2JsFailureKind.F2, error.message, status, error = error)
        } else {
            failure(HttpF2JsFailureKind.HTTP, text.ifBlank { "HTTP $status" }, status, body = text)
        }
    )
}

// A body is the array of the results, or a single result.
@Suppress("TooGenericExceptionCaught") // JSON.parse fails with a JS SyntaxError, which Kotlin sees as a Throwable
internal suspend fun <RESPONSE> HttpF2Client.results(response: HttpResponse): Array<JsonOf<RESPONSE>> {
    val text = response.text(this)
    if (text.isBlank()) return emptyArray()
    val parsed: dynamic = try {
        JSON.parse(text)
    } catch (e: Throwable) {
        val status = response.status.value
        throw Rejection(failure(HttpF2JsFailureKind.UNREADABLE, e.message ?: "No JSON", status, body = text), e)
    }
    return if (isArray(parsed)) {
        parsed.unsafeCast<Array<JsonOf<RESPONSE>>>()
    } else {
        arrayOf(parsed)
    }
}
