package f2.dsl.cqrs.page

actual interface OffsetPaginationDTO {
	actual val offset: Int
	actual val limit: Int
}

actual interface PagePaginationDTO {
	actual val page: Int?
	actual val size: Int?
}
