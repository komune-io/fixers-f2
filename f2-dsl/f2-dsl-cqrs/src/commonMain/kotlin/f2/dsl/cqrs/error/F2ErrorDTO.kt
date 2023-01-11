package f2.dsl.cqrs.error

import f2.dsl.cqrs.exception.F2Exception
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

@JsExport
@JsName("F2ErrorDTO")
interface F2ErrorDTO {
	val id: String
	val timestamp: String
	val code: Int
	val requestId: String?
	val message: String
}

@Serializable
@JsExport
@JsName("F2Error")
open class F2Error(
	override val id: String,
	override val timestamp: String,
	override val code: Int,
	override val message: String,
	override val requestId: String? = null,
) : F2ErrorDTO {
	override fun toString(): String {
		return "F2Error(timestamp='$timestamp', code=$code, requestId='$requestId', message='$message')"
	}
}
