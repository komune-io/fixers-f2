package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BatchTest {

    @Test
    fun `test batch function with default concurrency`() = runTest {
        val input = (1..10).toList().asFlow()
        val batch = Batch(size = 3, concurrency = 2)
        val result = input.batch(batch) { it.map { it * 2 } }.toList()

        val expected = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
        assertEquals(expected, result)
    }

    @Test
    fun `test batch function with single element batches`() = runTest {
        val input = (1..5).toList().asFlow()
        val batch = Batch(size = 1, concurrency = 1)
        val result = input.batch(batch) { it.map { it * 2 } }.toList()

        val expected = listOf(2, 4, 6, 8, 10)
        assertEquals(expected, result)
    }

    @Test
    fun `test batch function with larger batch size than input`() = runTest {
        val input = (1..3).toList().asFlow()
        val batch = Batch(size = 5, concurrency = 1)
        val result = input.batch(batch) { it.map { it * 2 } }.toList()

        val expected = listOf(2, 4, 6)
        assertEquals(expected, result)
    }
}