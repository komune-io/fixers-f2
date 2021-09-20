package f2.dsl.cqrs.page

@JsExport
@JsName("PageDTO")
actual external interface PageDTO<out OBJECT> {
	@JsName("total")
	actual val total: Int
	@JsName("list")
	actual val list: List<out OBJECT>
}
