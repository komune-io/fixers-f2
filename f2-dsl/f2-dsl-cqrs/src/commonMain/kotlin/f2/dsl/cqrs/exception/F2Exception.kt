package f2.dsl.cqrs.exception

import f2.dsl.cqrs.error.ErrorDTO
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("F2Exception")
open class F2Exception(
	val id: String,
	val error: ErrorDTO<*>,
) : Exception(error.description)
