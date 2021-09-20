package f2.dsl.cqrs.page

import f2.dsl.cqrs.Event
import f2.dsl.cqrs.Query
import kotlinx.serialization.descriptors.StructureKind

@JsExport
@JsName("PageQuery")
actual external interface PageQueryDTO : Query {
	actual val pagination: OffsetPaginationDTO?
}

@JsExport
@JsName("PageQueryResult")
actual external interface PageQueryResultDTO<out OBJECT> : Event {
	actual val pagination: OffsetPaginationDTO?
	actual val page: PageDTO<out OBJECT>
}
