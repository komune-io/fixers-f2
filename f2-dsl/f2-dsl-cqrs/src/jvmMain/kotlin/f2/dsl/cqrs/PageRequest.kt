package f2.dsl.cqrs

actual interface PageRequest {
	actual val page: Int?
	actual val size: Int?
}
