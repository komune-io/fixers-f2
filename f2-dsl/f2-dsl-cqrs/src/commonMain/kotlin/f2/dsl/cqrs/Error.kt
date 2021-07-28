package f2.dsl.cqrs

import kotlin.js.JsExport
import kotlin.js.JsName

expect interface Error<PAYLOAD> {
	val severity: ErrorSeverity
	val type: String
	val description: String
	val date: String
	val payload: PAYLOAD
}


@JsExport
@JsName("ErrorSeverity")
open class ErrorSeverity(val severity: String)

@JsExport
@JsName("ErrorSeverityWarning")
class ErrorSeverityWarning : ErrorSeverity("warning")

@JsExport
@JsName("AlertSeverityError")
class ErrorSeverityError : ErrorSeverity("error")