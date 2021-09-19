package f2.dsl.cqrs.error

@JsExport
@JsName("ErrorDTO")
actual external interface ErrorDTO<PAYLOAD> {
	@JsName("severity")
	actual val severity: ErrorSeverity

	@JsName("type")
	actual val type: String

	@JsName("description")
	actual val description: String

	@JsName("date")
	actual val date: String

	@JsName("payload")
	actual val payload: PAYLOAD
}
