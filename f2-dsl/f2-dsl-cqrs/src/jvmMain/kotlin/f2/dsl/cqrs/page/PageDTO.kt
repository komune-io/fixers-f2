package f2.dsl.cqrs.page

actual interface PageDTO<OBJECT> {
	actual val pagination: OffsetPaginationDTO?
	actual val total: Int
	actual val list: List<OBJECT>
}
