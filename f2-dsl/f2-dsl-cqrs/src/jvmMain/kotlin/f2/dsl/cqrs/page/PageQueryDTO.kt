package f2.dsl.cqrs.page

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query

actual interface PageQueryDTO : Query {
	actual val pagination: OffsetPaginationDTO?
}

actual interface PageQueryResultDTO<out OBJECT> : Event {
	actual val pagination: OffsetPaginationDTO?
	actual val page: PageDTO<out OBJECT>
}
