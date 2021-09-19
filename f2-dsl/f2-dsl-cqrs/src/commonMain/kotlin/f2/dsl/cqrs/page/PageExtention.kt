package f2.dsl.cqrs.page

fun <OBJECT>  OffsetPaginationDTO.result(list: List<OBJECT>, total: Int): Page<OBJECT> {
	return Page(
		pagination = OffsetPagination(
			offset = this.offset,
			limit = this.limit
		),
		list = list,
		total = total
	)
}
