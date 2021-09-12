package f2.dsl.cqrs

actual interface Error<PAYLOAD> {
	actual val severity: ErrorSeverity
	actual val type: String
	actual val description: String
	actual val date: String
	actual val payload: PAYLOAD
}
