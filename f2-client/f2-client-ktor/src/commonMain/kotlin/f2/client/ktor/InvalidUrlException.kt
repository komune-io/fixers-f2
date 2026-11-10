package f2.client.ktor

import kotlin.js.JsExport

/** A URL an F2 client cannot call: it starts with neither `http:` nor `https:`. */
@JsExport
class InvalidUrlException(url: String) :
    IllegalArgumentException("Invalid Url[$url] must start with one of http:, https:")
