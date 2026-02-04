package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FlattenConcurrentlyTest {

    @Test
    suspend fun `test flattenConcurrently with Flow of Flows`() {
        val flow = flowOf(
            flowOf(1, 2, 3),
            flowOf(4, 5, 6),
            flowOf(7, 8, 9)
        )
        val result = flow.flattenConcurrently().toList()
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    @Test
    suspend fun `test flattenConcurrently with Flow of Lists`() {
        val flow = flowOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val result = flow.flattenConcurrently().toList()
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    @Test
    suspend fun `test flattenConcurrently with custom concurrency`() {
        val flow = flowOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val result = flow.flattenConcurrently(concurrency = 2).toList()
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}
