package f2.dsl.cqrs.page

import org.springframework.data.domain.PageRequest

/**
 * Maps an [OffsetPagination] (skip [OffsetPagination.offset] items, then return at most
 * [OffsetPagination.limit] items) onto a Spring [PageRequest] (page index + page size).
 *
 * `limit` is a count, not an end index, so it maps directly to the page size and the page index is
 * `offset / limit`.
 *
 * Non-aligned offsets: a [PageRequest] can only express offsets that fall on a page boundary, so an
 * offset that is not a multiple of `limit` is rounded **down** to the page that contains it — e.g.
 * `offset=5, limit=10` yields page 0, i.e. items 0..9. Callers needing an exact non-aligned window
 * must slice the returned page themselves.
 *
 * A null pagination means "no pagination": the first page, unbounded.
 */
fun OffsetPagination?.toPageRequest(): PageRequest {
    if (this == null) {
        return PageRequest.of(0, Int.MAX_VALUE)
    }
    require(limit >= 1) { "Page size must not be less than one" }
    require(offset >= 0) { "Offset must not be less than zero" }
    return PageRequest.of(offset / limit, limit)
}
