package f2.dsl.cqrs.page

@JsExport
@JsName("PageDTO")
actual external interface PageDTO<out OBJECT> {
	actual val total: Int
	actual val items: List<OBJECT>
}
