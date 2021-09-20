package f2.dsl.cqrs.page

actual interface PageDTO<out OBJECT> {
	actual val total: Int
	actual val list: List<out OBJECT>
}
