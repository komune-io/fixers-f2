package f2.dsl.cqrs.page

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("Pagination")
sealed interface Pagination

@Serializable
@JsExport
@JsName("OffsetPaginationDTO")
interface OffsetPaginationDTO {
	val offset: Int
	val limit: Int
}

@Serializable
@JsExport
@JsName("OffsetPagination")
class OffsetPagination(
	override val offset: Int,
	override val limit: Int,
) : OffsetPaginationDTO, Pagination


@Serializable
@JsExport
@JsName("PagePaginationDTO")
interface PagePaginationDTO {
	val page: Int?
	val size: Int?
}

@Serializable
@JsExport
@JsName("PagePagination")
class PagePagination(
	override val page: Int?,
	override val size: Int?
): PagePaginationDTO, Pagination
