package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FlattenConcurrentlyTest {

    @Test
    fun `test flattenConcurrently with Flow of Flows`() = runTest {
        val flow = flowOf(
            flowOf(1, 2, 3),
            flowOf(4, 5, 6),
            flowOf(7, 8, 9)
        )
        val result = flow.flattenConcurrently().toList()
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    @Test
    fun `test flattenConcurrently with Flow of Lists`() = runTest {
        val flow = flowOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val result = flow.flattenConcurrently().toList()
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    @Test
    fun `test flattenConcurrently with custom concurrency`() = runTest {
        val flow = flowOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val result = flow.flattenConcurrently(concurrency = 2).toList()
        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}