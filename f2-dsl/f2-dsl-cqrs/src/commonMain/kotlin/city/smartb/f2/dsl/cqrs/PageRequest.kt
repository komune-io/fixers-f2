package city.smartb.f2.dsl.cqrs

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("PageRequest")
interface PageRequest {
    @JsName("page")
    val page : Int?

    @JsName("size")
    val size : Int?
}
