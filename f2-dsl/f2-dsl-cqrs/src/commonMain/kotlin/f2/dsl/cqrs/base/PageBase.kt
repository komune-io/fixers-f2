package f2.dsl.cqrs.base

import f2.dsl.cqrs.Page
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("PageBase")
class PageBase<OBJECT>(
	override val page: Int,
	override val size: Int,
	override val total: Long,
	override val list: List<OBJECT>,
) : Page<OBJECT>