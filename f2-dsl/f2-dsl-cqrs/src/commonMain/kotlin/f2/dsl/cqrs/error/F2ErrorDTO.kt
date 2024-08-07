package f2.dsl.cqrs.error

import com.benasher44.uuid.uuid4
import f2.dsl.cqrs.Problem
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

@JsExport
@JsName("F2ErrorDTO")
interface F2ErrorDTO: Problem {
	val id: String?
	val timestamp: String
	val code: Int
	val requestId: String?
	val message: String
}

@Serializable
@JsExport
@JsName("F2Error")
open class F2Error(
	override val message: String,
	override val id: String? = uuid4().toString(),
	override val timestamp: String = Clock.System.now().toString(),
	override val code: Int = 500,
	override val requestId: String? = null,
) : F2ErrorDTO {
	override fun toString(): String {
		return "F2Error(timestamp='$timestamp', code=$code, requestId='$requestId', message='$message')"
	}
}
