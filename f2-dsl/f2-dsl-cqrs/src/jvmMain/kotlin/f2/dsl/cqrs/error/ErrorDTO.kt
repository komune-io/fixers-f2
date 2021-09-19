package f2.dsl.cqrs.error

actual interface ErrorDTO<PAYLOAD> {
	actual val severity: ErrorSeverity
	actual val type: String
	actual val description: String
	actual val date: String
	actual val payload: PAYLOAD
}
