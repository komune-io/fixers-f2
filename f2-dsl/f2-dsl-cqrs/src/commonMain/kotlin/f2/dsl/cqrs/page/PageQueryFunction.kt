package f2.dsl.cqrs.page

import f2.dsl.cqrs.Query

interface PageQuery: Query {
	val pagination: OffsetPaginationDTO?
}

interface PageQueryResult<OBJECT>: Query {
	val page: Page<OBJECT>?
}
