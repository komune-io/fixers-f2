package f2.dsl.cqrs.page

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.StructureKind

@Serializable
expect interface PageDTO<out OBJECT> {
	val total: Int
	val items: List<OBJECT>
}

@Serializable
@JsExport
@JsName("Page")
class Page<out OBJECT>(
	override val total: Int,
	override val items: List<OBJECT>,
) : PageDTO<OBJECT>
