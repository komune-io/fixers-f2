package f2.dsl.cqrs.page

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query

expect interface PageQueryDTO : Query {
	val pagination: OffsetPaginationDTO?
}

expect interface PageQueryResultDTO<out OBJECT> : Event {
	val pagination: OffsetPaginationDTO?
	val page: PageDTO<out OBJECT>
}

class PageQuery(
	override val pagination: OffsetPaginationDTO?,
) : PageQueryDTO


class PageQueryResult<out OBJECT>(
	override val pagination: OffsetPaginationDTO?,
	override val page: Page<out OBJECT>,
) : PageQueryResultDTO<OBJECT>
