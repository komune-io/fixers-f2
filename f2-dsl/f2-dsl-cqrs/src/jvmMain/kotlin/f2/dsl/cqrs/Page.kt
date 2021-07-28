package f2.dsl.cqrs

actual interface Page<OBJECT> {
	actual val page: Int
	actual val size: Int
	actual val total: Long
	actual val list: List<OBJECT>
}