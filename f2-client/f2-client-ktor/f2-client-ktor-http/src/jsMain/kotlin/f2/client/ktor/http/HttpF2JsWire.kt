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

internal suspend fun HttpF2Client.postAll(route: String, messages: dynamic): HttpResponse {
    if (!isArray(messages)) throw Rejection(typeError("The messages of $route are an array"))
    jsonProblem(messages, js("new Set()"))?.let { problem ->
        throw Rejection(typeError("A message of $route $problem"))
    }
    val body = JSON.stringify(messages)
    return request { httpClient.post("$urlBase/$route") { setBody(TextContent(body, ContentType.Application.Json)) } }
}

// Why a value is no plain JSON, or null: a class instance at any depth, or a cycle through [ancestors].
private fun jsonProblem(value: dynamic, ancestors: dynamic): String? = when (jsTypeOf(value)) {
    "string", "number", "boolean", "undefined" -> null
    "object" -> objectProblem(value, ancestors)
    else -> "is plain JSON, not a ${jsTypeOf(value)}"
}

private fun objectProblem(value: dynamic, ancestors: dynamic): String? =
    when {
        value == null -> {
            null
        }
        ancestors.has(value) as Boolean -> {
            "holds a cycle, which JSON cannot write"
        }
        !isArray(value) && isClassInstance(value) -> {
            "is plain JSON: no instance of a class, at any depth"
        }
        else -> {
            ancestors.add(value)
            val problem = js("Object").values(value).unsafeCast<Array<dynamic>>()
                .firstNotNullOfOrNull { child -> jsonProblem(child, ancestors) }
            ancestors.delete(value)
            problem
        }
    }

// An object whose prototype is neither Object's nor none: an instance of a class, a Kotlin one included.
private fun isClassInstance(value: dynamic): Boolean {
    val prototype: dynamic = js("Object").getPrototypeOf(value)
    return prototype != js("Object.prototype") && prototype != null
}

private fun isArray(value: dynamic): Boolean = js("Array").isArray(value) as Boolean

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
