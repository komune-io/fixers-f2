package f2.dsl.cqrs.page

actual interface PageDTO<out OBJECT> {
	actual val total: Int
	actual val items: List<OBJECT>
}
