package f2.dsl.cqrs

@JsExport
@JsName("Page")
actual external interface Page<OBJECT> {
	@JsName("page")
	actual val page: Int

	@JsName("size")
	actual val size: Int

	@JsName("total")
	actual val total: Long

	@JsName("list")
	actual val list: List<OBJECT>
}
