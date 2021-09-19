package f2.dsl.cqrs.page

interface PageResult<OBJECT> {
	val page: PageDTO<OBJECT>
}
