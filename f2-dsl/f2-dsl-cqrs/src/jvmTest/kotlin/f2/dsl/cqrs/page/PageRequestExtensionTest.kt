package f2.dsl.cqrs.page

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest

class PageRequestExtensionTest {

    @Test
    fun `offset zero maps to the first page sized by limit`() {
        assertThat(OffsetPagination(offset = 0, limit = 10).toPageRequest())
            .isEqualTo(PageRequest.of(0, 10))
    }

    @Test
    fun `offset equal to limit maps to the second page`() {
        assertThat(OffsetPagination(offset = 10, limit = 10).toPageRequest())
            .isEqualTo(PageRequest.of(1, 10))
    }

    @Test
    fun `offset of several whole pages maps to the matching page index`() {
        assertThat(OffsetPagination(offset = 75, limit = 25).toPageRequest())
            .isEqualTo(PageRequest.of(3, 25))
    }

    @Test
    fun `page size always mirrors the limit`() {
        assertThat(OffsetPagination(offset = 40, limit = 20).toPageRequest().pageSize).isEqualTo(20)
        assertThat(OffsetPagination(offset = 0, limit = 1).toPageRequest().pageSize).isEqualTo(1)
    }

    @Test
    fun `the resulting page request skips exactly the requested offset when aligned`() {
        val pagination = OffsetPagination(offset = 60, limit = 20)

        val pageRequest = pagination.toPageRequest()

        assertThat(pageRequest.offset).isEqualTo(pagination.offset.toLong())
    }

    @Test
    fun `a non aligned offset is rounded down to the page containing it`() {
        // offset 5 with a page size of 10 falls inside page 0 (items 0..9): PageRequest cannot
        // express a mid-page start, so the offset is floored to the containing page boundary.
        assertThat(OffsetPagination(offset = 5, limit = 10).toPageRequest())
            .isEqualTo(PageRequest.of(0, 10))

        assertThat(OffsetPagination(offset = 25, limit = 10).toPageRequest())
            .isEqualTo(PageRequest.of(2, 10))
    }

    @Test
    fun `an offset smaller than the limit stays on the first page`() {
        assertThat(OffsetPagination(offset = 10, limit = 20).toPageRequest())
            .isEqualTo(PageRequest.of(0, 20))
    }

    @Test
    fun `an unbounded limit keeps everything on the first page`() {
        assertThat(OffsetPagination(offset = 0, limit = Int.MAX_VALUE).toPageRequest())
            .isEqualTo(PageRequest.of(0, Int.MAX_VALUE))
    }

    @Test
    fun `null pagination defaults to the first page with an unbounded size`() {
        assertThat((null as OffsetPagination?).toPageRequest())
            .isEqualTo(PageRequest.of(0, Int.MAX_VALUE))
    }

    @Test
    fun `a limit below one is rejected`() {
        assertThatThrownBy { OffsetPagination(offset = 0, limit = 0).toPageRequest() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Page size must not be less than one")

        assertThatThrownBy { OffsetPagination(offset = 10, limit = -5).toPageRequest() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Page size must not be less than one")
    }

    @Test
    fun `a negative offset is rejected`() {
        assertThatThrownBy { OffsetPagination(offset = -1, limit = 10).toPageRequest() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Offset must not be less than zero")
    }
}
