package city.smartb.f2.dsl.cqrs.exception

import city.smartb.f2.dsl.cqrs.Error
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("R2Exception")
open class S2Exception(
        val id: String,
        val error: Error<*>
): Exception(error.description)
