package f2.dsl.cqrs.error

import f2.dsl.cqrs.Problem
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
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
@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
open class F2Error(
    override val message: String,
    override val id: String? = Uuid.random().toString(),
    override val timestamp: String = Clock.System.now().toString(),
    override val code: Int = 500,
    override val requestId: String? = null,
) : F2ErrorDTO {
	override fun toString(): String {
		return "F2Error(timestamp='$timestamp', code=$code, requestId='$requestId', message='$message')"
	}
}
