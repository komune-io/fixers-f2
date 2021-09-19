package f2.dsl.cqrs.error

import kotlin.js.JsExport
import kotlin.js.JsName

expect interface ErrorDTO<PAYLOAD> {
	val severity: ErrorSeverity
	val type: String
	val description: String
	val date: String
	val payload: PAYLOAD
}

@JsExport
@JsName("Error")
open class Error<PAYLOAD>(
	override val type: String,
	override val description: String,
	override val date: String,
	override val payload: PAYLOAD,
	override val severity: ErrorSeverity,
) : ErrorDTO<PAYLOAD>

@JsExport
@JsName("ErrorSeverity")
open class ErrorSeverity(val severity: String)

@JsExport
@JsName("ErrorSeverityWarning")
class ErrorSeverityWarning : ErrorSeverity("warning")

@JsExport
@JsName("AlertSeverityError")
class ErrorSeverityError : ErrorSeverity("error")
