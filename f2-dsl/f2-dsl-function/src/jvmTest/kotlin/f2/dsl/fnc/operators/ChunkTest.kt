package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChunkTest {

    @Test
    suspend fun `test chunk with transformation`() {
        val flow = flowOf(1, 2, 3, 4, 5, 6)
        val chunkSize = 2
        val result = flow.chunk(chunkSize) { chunk -> chunk.map { it * 2 } }.toList()
        assertThat(result).isEqualTo(listOf(listOf(2, 4), listOf(6, 8), listOf(10, 12)))
    }

    @Test
    suspend fun `test chunk without transformation`() {
        val flow = flowOf(1, 2, 3, 4, 5, 6)
        val chunkSize = 3
        val result = flow.chunk(chunkSize).toList()
        assertThat(result).isEqualTo(listOf(listOf(1, 2, 3), listOf(4, 5, 6)))
    }

    @Test
    suspend fun `test chunk with remaining elements`() {
        val flow = flowOf(1, 2, 3, 4, 5)
        val chunkSize = 2
        val result = flow.chunk(chunkSize).toList()
        assertThat(result).isEqualTo(listOf(listOf(1, 2), listOf(3, 4), listOf(5)))
    }

    @Test
    suspend fun `test chunk with empty flow`() {
        val flow = flowOf<Int>()
        val result = flow.chunk(3).toList()
        assertThat(result).isEmpty()
    }

    @Test
    suspend fun `test chunkFlow transforms chunks to flows`() {
        val flow = flowOf(1, 2, 3, 4)
        val result = flow.chunkFlow(2) { chunk ->
            flowOf(*chunk.map { it * 10 }.toTypedArray())
        }.toList().map { it.toList() }
        assertThat(result).isEqualTo(listOf(listOf(10, 20), listOf(30, 40)))
    }

    @Test
    suspend fun `test chunkFlow with remaining elements`() {
        val flow = flowOf(1, 2, 3, 4, 5)
        val result = flow.chunkFlow(2) { chunk ->
            flowOf(*chunk.toTypedArray())
        }.toList().map { it.toList() }
        assertThat(result).isEqualTo(listOf(listOf(1, 2), listOf(3, 4), listOf(5)))
    }

    @Test
    suspend fun `test chunkFlow with empty flow`() {
        val flow = flowOf<Int>()
        val result = flow.chunkFlow(3) { flowOf(*it.toTypedArray()) }.toList()
        assertThat(result).isEmpty()
    }

    @Test
    fun `test CHUNK_DEFAULT_SIZE constant`() {
        assertThat(CHUNK_DEFAULT_SIZE).isEqualTo(128)
    }
}
