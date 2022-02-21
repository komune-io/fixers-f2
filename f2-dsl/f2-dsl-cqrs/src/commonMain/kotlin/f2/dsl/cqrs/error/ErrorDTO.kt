package f2.dsl.cqrs.error

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("ErrorDTO")
interface ErrorDTO<PAYLOAD> {
	val severity: ErrorSeverity
	val type: String
	val description: String
	val date: String
	val payload: PAYLOAD
}

@Serializable
@JsExport
@JsName("Error")
open class Error<PAYLOAD>(
	override val type: String,
	override val description: String,
	override val date: String,
	override val payload: PAYLOAD,
	override val severity: ErrorSeverity,
) : ErrorDTO<PAYLOAD>

@Serializable
@JsExport
@JsName("ErrorSeverity")
open class ErrorSeverity(val severity: String)

@Serializable
@JsExport
@JsName("ErrorSeverityWarning")
class ErrorSeverityWarning : ErrorSeverity("warning")

@Serializable
@JsExport
@JsName("AlertSeverityError")
class ErrorSeverityError : ErrorSeverity("error")
