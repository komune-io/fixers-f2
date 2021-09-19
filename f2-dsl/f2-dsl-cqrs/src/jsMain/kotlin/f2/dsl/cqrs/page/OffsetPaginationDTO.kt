package f2.dsl.cqrs.page

@JsExport
@JsName("OffsetPaginationDTO")
actual external interface OffsetPaginationDTO {
	@JsName("offset")
	actual val offset: Int

	@JsName("limit")
	actual val limit: Int
}

@JsExport
@JsName("PagePaginationDTO")
actual external interface PagePaginationDTO {
	@JsName("page")
	actual val page: Int?

	@JsName("size")
	actual val size: Int?
}
