package city.smartb.f2.dsl.cqrs.exception

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("R2NotFoundException")
class S2NotFoundException(
        message : String
) : Exception(message)