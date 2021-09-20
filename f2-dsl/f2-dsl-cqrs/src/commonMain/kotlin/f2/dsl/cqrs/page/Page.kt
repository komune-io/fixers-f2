package f2.dsl.cqrs.page

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

@Serializable
expect interface PageDTO<out OBJECT> {
	val total: Int
	val list: List<out OBJECT>
}

@Serializable
@JsExport
@JsName("Page")
class Page<out OBJECT>(
	override val total: Int,
	override val list: List<out OBJECT>,
) : PageDTO<OBJECT>
