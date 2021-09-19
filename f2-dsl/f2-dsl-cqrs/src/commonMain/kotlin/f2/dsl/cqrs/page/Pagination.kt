package f2.dsl.cqrs.page

import kotlin.js.JsExport
import kotlin.js.JsName

sealed interface Pagination

expect interface OffsetPaginationDTO {
	val offset: Int
	val limit: Int
}

@JsExport
@JsName("OffsetRequest")
class OffsetPagination(
	override val offset: Int,
	override val limit: Int,
) : OffsetPaginationDTO, Pagination


expect interface PagePaginationDTO {
	val page: Int?
	val size: Int?
}

@JsExport
@JsName("PagePagination")
class PagePagination(
	override val page: Int?,
	override val size: Int?
): PagePaginationDTO, Pagination
