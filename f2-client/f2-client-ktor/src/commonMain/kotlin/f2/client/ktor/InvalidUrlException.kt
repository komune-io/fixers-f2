package f2.client.ktor

import kotlin.js.JsExport

@JsExport
class InvalidUrlException(url: String): Exception("$url is invalid")
