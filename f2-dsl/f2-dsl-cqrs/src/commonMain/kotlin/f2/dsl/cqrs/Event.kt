package f2.dsl.cqrs

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName


@Serializable
@JsExport
@JsName("Event")
interface Event: Message
