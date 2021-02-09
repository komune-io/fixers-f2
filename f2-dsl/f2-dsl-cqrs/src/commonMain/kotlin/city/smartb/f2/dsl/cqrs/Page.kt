package city.smartb.f2.dsl.cqrs

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName


@Serializable
@JsExport
@JsName("Page")
interface Page<OBJECT> {
    @JsName("page")
    val page : Int

    @JsName("size")
    val size : Int

    @JsName("total")
    val total : Long

    @JsName("list")
    val list  : List<OBJECT>
}