package f2.dsl.cqrs.page

@JsExport
@JsName("PageDTO")
actual external interface PageDTO<OBJECT> {
	@JsName("pagination")
	actual val pagination: OffsetPaginationDTO?
	@JsName("total")
	actual val total: Int
	@JsName("list")
	actual val list: List<OBJECT>
}
