package f2.dsl.cqrs

@JsExport
@JsName("PageRequest")
actual external interface PageRequest {
	@JsName("page")
	actual val page: Int?

	@JsName("size")
	actual val size: Int?

}