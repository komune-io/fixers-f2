package f2.client.ktor.http

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

// The headers a call of HttpF2JsClient sends, as its provider answered them.

internal fun HttpRequestBuilder.withHeaders(headers: List<Pair<String, String>>) =
    headers.forEach { (name, value) -> header(name, value) }

// RFC 9110 token; a value holds no line break.
private val HEADER_NAME = Regex("""^[!#$%&'*+.^_`|~0-9A-Za-z-]+$""")
// Ktor's list leaves Content-Type out, which it would let a header overwrite.
private val WRITTEN_BY_THE_CLIENT = (
    HttpHeaders.UnsafeHeadersList +
        listOf(HttpHeaders.ContentType, HttpHeaders.ContentLength, HttpHeaders.TransferEncoding, HttpHeaders.Upgrade)
).map { it.lowercase() }.toSet()

// The headers a provider answered, as sent, or a TypeError: a plain object whose values are strings, no header the
// client writes itself.
internal fun checkedHeaders(route: String, provided: dynamic): List<Pair<String, String>> {
    fun refused(problem: String): Nothing = throw Rejection(typeError("The headers of $route $problem"))
    if (!isPlainObject(provided)) refused("are a plain object of strings, not ${jsTypeOf(provided)}")
    return jsObject.entries(provided).unsafeCast<Array<Array<dynamic>>>().map { (name, value) ->
        val key = name.unsafeCast<String>()
        when {
            !HEADER_NAME.matches(key) -> refused("name no header: \"$key\"")
            jsTypeOf(value) != "string" -> refused("hold a ${jsTypeOf(value)} for $key, not a string")
            value.unsafeCast<String>().any { it == '\r' || it == '\n' } -> refused("hold a line break in $key")
            key.lowercase() in WRITTEN_BY_THE_CLIENT -> refused("set $key, which the client writes itself")
        }
        key to value.unsafeCast<String>()
    }
}

private fun isPlainObject(value: dynamic): Boolean {
    if (jsTypeOf(value) != "object" || value == null) return false
    return !isArray(value) && !isClassInstance(value)
}
