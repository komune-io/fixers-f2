package city.smartb.f2.client.ktor

import kotlin.js.JsExport

@JsExport
sealed class Protocol
@JsExport
object HTTP: Protocol()
@JsExport
object HTTPS: Protocol()
@JsExport
object WS: Protocol()
@JsExport
object WSS: Protocol()
@JsExport
object TCP: Protocol()

