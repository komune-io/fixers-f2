package city.smartb.f2.dsl.cqrs

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("Error")
interface  Error<PAYLOAD> {
        @JsName("severity")
        val severity: ErrorSeverity
        @JsName("type")
        val type: String
        @JsName("description")
        val description: String
        @JsName("date")
        val date: String
        @JsName("payload")
        val payload: PAYLOAD
}

@JsExport
@JsName("ErrorSeverity")
sealed class ErrorSeverity(val severity: String)

@JsExport
@JsName("ErrorSeverityWarning")
class ErrorSeverityWarning: ErrorSeverity("warning")

@JsExport
@JsName("AlertSeverityError")
class AlertSeverityError: ErrorSeverity("error")