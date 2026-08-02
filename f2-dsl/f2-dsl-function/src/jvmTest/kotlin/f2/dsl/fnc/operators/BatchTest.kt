package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BatchTest {

    @Test
    suspend fun `test batch function with default concurrency`() {
        val input = (1..10).toList().asFlow()
        val batch = Batch(size = 3, concurrency = 2)
        val result = input.batch(batch) { it.map { it * 2 } }.toList()

        val expected = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
        assertEquals(expected, result)
    }

    @Test
    suspend fun `test batch function with single element batches`() {
        val input = (1..5).toList().asFlow()
        val batch = Batch(size = 1, concurrency = 1)
        val result = input.batch(batch) { it.map { it * 2 } }.toList()

        val expected = listOf(2, 4, 6, 8, 10)
        assertEquals(expected, result)
    }

    @Test
    suspend fun `test batch function with larger batch size than input`() {
        val input = (1..3).toList().asFlow()
        val batch = Batch(size = 5, concurrency = 1)
        val result = input.batch(batch) { it.map { it * 2 } }.toList()

        val expected = listOf(2, 4, 6)
        assertEquals(expected, result)
    }

    @Test
    suspend fun `test batchFlow function emits transformed batches`() {
        val input = (1..6).toList().asFlow()
        val batch = Batch(size = 2, concurrency = 1)
        val result = input.batchFlow(batch) { items ->
            listOf(items.map { it * 2 }).asFlow()
        }.toList()

        val expected = listOf(
            listOf(2, 4),
            listOf(6, 8),
            listOf(10, 12)
        )
        assertEquals(expected, result)
    }

    @Test
    suspend fun `test batch default configuration`() {
        val batch = Batch()

        assertEquals(BATCH_DEFAULT_SIZE, batch.size)
        assertEquals(BATCH_DEFAULT_CONCURRENCY, batch.concurrency)
    }
}
