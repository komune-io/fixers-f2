package f2.dsl.cqrs.page

fun <OBJECT> OffsetPaginationDTO.result(list: List<OBJECT>, total: Int): PageQueryResultDTO<OBJECT> {
	return PageQueryResult(
		pagination = OffsetPagination(
			offset = this.offset,
			limit = this.limit
		),
		page = Page(
			list = list,
			total = total
		)
	)
}
