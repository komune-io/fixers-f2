package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChunkTest {

    @Test
    fun `test chunk with transformation`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5, 6)
        val chunkSize = 2
        val result = flow.chunk(chunkSize) { chunk -> chunk.map { it * 2 } }.toList()
        assertThat(result).isEqualTo(listOf(listOf(2, 4), listOf(6, 8), listOf(10, 12)))
    }

    @Test
    fun `test chunk without transformation`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5, 6)
        val chunkSize = 3
        val result = flow.chunk(chunkSize).toList()
        assertThat(result).isEqualTo(listOf(listOf(1, 2, 3), listOf(4, 5, 6)))
    }

    @Test
    fun `test chunk with remaining elements`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5)
        val chunkSize = 2
        val result = flow.chunk(chunkSize).toList()
        assertThat(result).isEqualTo(listOf(listOf(1, 2), listOf(3, 4), listOf(5)))
    }
}