package f2.dsl.cqrs.error

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("F2ErrorDTO")
interface F2ErrorDTO {
	val timestamp: String
	val status: Int
	val code: Int
	val requestId: String
	val error: String
}

@Serializable
@JsExport
@JsName("F2Error")
open class F2Error(
	override val timestamp: String,
	override val status: Int,
	override val code: Int,
	override val requestId: String,
	override val error: String
) : F2ErrorDTO
