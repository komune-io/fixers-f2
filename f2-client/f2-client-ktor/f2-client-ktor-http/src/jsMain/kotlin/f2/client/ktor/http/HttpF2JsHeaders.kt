package f2.client.ktor.http

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

// The headers a call of HttpF2JsClient sends, as its provider answered them.

internal fun HttpRequestBuilder.withHeaders(headers: List<Pair<String, String>>) =
    headers.forEach { (name, value) -> header(name, value) }

// RFC 9110 token.
private val HEADER_NAME = Regex("""^[!#$%&'*+.^_`|~0-9A-Za-z-]+$""")

// Ktor's list leaves Content-Type out, which it would let a header overwrite; Accept is the client's too.
private val WRITTEN_BY_THE_CLIENT = (
    HttpHeaders.UnsafeHeadersList +
        listOf(HttpHeaders.ContentType, HttpHeaders.ContentLength, HttpHeaders.TransferEncoding, HttpHeaders.Upgrade) +
        HttpHeaders.Accept
).map { it.lowercase() }.toSet()

// The Fetch standard's forbidden request headers: a browser drops them without a word.
private val DROPPED_BY_FETCH = setOf(
    "accept-charset", "accept-encoding", "access-control-request-headers", "access-control-request-method",
    "connection", "cookie", "cookie2", "date", "dnt", "expect", "host", "keep-alive", "origin", "referer",
    "set-cookie", "te", "trailer", "via",
)
private val DROPPED_BY_FETCH_PREFIXES = listOf("proxy-", "sec-")

// What Ktor and fetch accept in a value: a tab, visible ASCII and Latin-1. Ktor's own refusal would quote the value.
private const val LATIN1_FIRST = 0x80
private const val LATIN1_LAST = 0xFF

private fun Char.isHeaderValueChar(): Boolean = this == '\t' || this in ' '..'~' || code in LATIN1_FIRST..LATIN1_LAST

private fun String.isDroppedByFetch(): Boolean =
    this in DROPPED_BY_FETCH || DROPPED_BY_FETCH_PREFIXES.any { prefix -> startsWith(prefix) }

// The headers a provider answered, as sent, or a TypeError that names the header, never its value: a plain object of
// strings, each name once whatever its case, no header the client writes itself or fetch would drop.
internal fun checkedHeaders(route: String, provided: dynamic): List<Pair<String, String>> {
    fun refused(problem: String): Nothing = throw Rejection(typeError("The headers of $route $problem"))
    if (!isPlainObject(provided)) refused("are a plain object of strings, not ${jsTypeOf(provided)}")
    val headers = jsObject.entries(provided).unsafeCast<Array<Array<dynamic>>>().map { (name, value) ->
        val key = name.unsafeCast<String>()
        val lowercase = key.lowercase()
        when {
            !HEADER_NAME.matches(key) -> refused("name no header: \"$key\"")
            jsTypeOf(value) != "string" -> refused("hold a ${jsTypeOf(value)} for $key, not a string")
            !value.unsafeCast<String>().all { it.isHeaderValueChar() } ->
                refused("hold in $key a control character or one beyond Latin-1")
            lowercase in WRITTEN_BY_THE_CLIENT -> refused("set $key, which the client writes itself")
            lowercase.isDroppedByFetch() -> refused("set $key, which fetch does not send")
        }
        key to value.unsafeCast<String>()
    }
    headers.groupBy { (name, _) -> name.lowercase() }.values.firstOrNull { it.size > 1 }?.let { same ->
        refused("name one header twice: ${same.joinToString { (name, _) -> name }}")
    }
    return headers
}

private fun isPlainObject(value: dynamic): Boolean {
    if (jsTypeOf(value) != "object" || value == null) return false
    return !isArray(value) && !isClassInstance(value)
}
