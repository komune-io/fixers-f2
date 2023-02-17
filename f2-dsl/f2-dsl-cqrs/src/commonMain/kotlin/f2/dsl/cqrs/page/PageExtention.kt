package f2.dsl.cqrs.page

fun <OBJECT> OffsetPaginationDTO.result(items: List<OBJECT>, total: Int): PageQueryResultDTO<OBJECT> {
	return PageQueryResult(
		pagination = OffsetPagination(
			offset = this.offset,
			limit = this.limit
		),
		items = items,
		total = total
	)
}

inline fun <T, reified R> PageDTO<T>.map(transform: (T) -> R): PageDTO<R> = Page(
	items = items.map(transform),
	total = total
)

inline fun <T, reified R> Page<T>.map(transform: (T) -> R): Page<R> = Page(
	items = items.map(transform),
	total = total
)

inline fun <T, reified R: Any> PageDTO<T>.mapNotNull(transform: (T) -> R?): PageDTO<R> = Page(
	items = items.mapNotNull(transform),
	total = total
)
