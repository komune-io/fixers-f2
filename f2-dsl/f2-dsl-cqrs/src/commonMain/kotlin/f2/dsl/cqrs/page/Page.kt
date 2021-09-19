package f2.dsl.cqrs.page

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

@Serializable
expect interface PageDTO<OBJECT> {
	val pagination: OffsetPaginationDTO?
	val total: Int
	val list: List<OBJECT>
}

@Serializable
@JsExport
@JsName("Page")
class Page<OBJECT>(
	override val pagination: OffsetPaginationDTO?,
	override val total: Int,
	override val list: List<OBJECT>,
) : PageDTO<OBJECT>
