package city.smartb.f2.dsl.cqrs.base

import city.smartb.f2.dsl.cqrs.Error
import city.smartb.f2.dsl.cqrs.ErrorSeverity
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("ErrorBase")
open class ErrorBase<PAYLOAD>(
        override val type: String,
        override val description: String,
        override val date: String,
        override val payload: PAYLOAD,
        override val severity: ErrorSeverity
) : Error<PAYLOAD>