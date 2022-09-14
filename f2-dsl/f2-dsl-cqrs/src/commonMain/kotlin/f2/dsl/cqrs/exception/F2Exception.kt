package f2.dsl.cqrs.exception

import f2.dsl.cqrs.error.F2Error
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("F2Exception")
open class F2Exception(
	val id: String,
	val error: F2Error
) : Exception(error.error)
