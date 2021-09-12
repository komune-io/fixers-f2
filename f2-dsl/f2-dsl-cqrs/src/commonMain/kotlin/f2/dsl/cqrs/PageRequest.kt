package f2.dsl.cqrs

expect interface PageRequest {
	val page: Int?
	val size: Int?
}
