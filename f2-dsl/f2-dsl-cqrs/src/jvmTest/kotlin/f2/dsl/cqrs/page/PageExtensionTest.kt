package f2.dsl.cqrs.page

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest

class PageExtensionTest {

    @Test
    fun `result builds a PageQueryResult from a pagination`() {
        val pagination = OffsetPagination(offset = 0, limit = 10)

        val result = pagination.result(items = listOf("a", "b"), total = 12)

        assertThat(result.items).containsExactly("a", "b")
        assertThat(result.total).isEqualTo(12)
        assertThat(result.pagination?.offset).isEqualTo(0)
        assertThat(result.pagination?.limit).isEqualTo(10)
    }

    @Test
    fun `map transforms page items`() {
        val page: PageDTO<Int> = Page(total = 2, items = listOf(1, 2))

        val mapped = page.map { it.toString() }

        assertThat(mapped.items).containsExactly("1", "2")
        assertThat(mapped.total).isEqualTo(2)
    }

    @Test
    fun `mapNotNull drops null transformations`() {
        val page: PageDTO<Int> = Page(total = 3, items = listOf(1, 2, 3))

        val mapped = page.mapNotNull { value -> value.takeIf { it % 2 == 1 } }

        assertThat(mapped.items).containsExactly(1, 3)
    }

    @Test
    fun `toPageRequest maps offset pagination to a page request`() {
        val pageRequest = OffsetPagination(offset = 0, limit = 10).toPageRequest()

        assertThat(pageRequest).isEqualTo(PageRequest.of(0, 10))
    }

    @Test
    fun `toPageRequest maps second page pagination`() {
        val pageRequest = OffsetPagination(offset = 10, limit = 20).toPageRequest()

        assertThat(pageRequest).isEqualTo(PageRequest.of(1, 10))
    }

    @Test
    fun `toPageRequest defaults to first page with max size on null`() {
        val pageRequest = (null as OffsetPagination?).toPageRequest()

        assertThat(pageRequest).isEqualTo(PageRequest.of(0, Int.MAX_VALUE))
    }
}
