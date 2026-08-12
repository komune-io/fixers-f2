package f2.dsl.fnc.operators

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest

class GroupByTest {

    @Test
    fun `test groupBy with integers`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5, 6)
        val result = flow.groupBy { it % 2 }.toList()
        val evenGroup = result.first { it.first == 0 }.second.toList()
        val oddGroup = result.first { it.first == 1 }.second.toList()
        assertEquals(listOf(2, 4, 6), (evenGroup).toList())
        assertEquals(listOf(1, 3, 5), (oddGroup).toList())
    }

    @Test
    fun `test groupBy with strings`() = runTest {
        val flow = flowOf("apple", "banana", "apricot", "blueberry")
        val result = flow.groupBy { it.first() }.toList()
        val aGroup = result.first { it.first == 'a' }.second.toList()
        val bGroup = result.first { it.first == 'b' }.second.toList()
        assertEquals(listOf("apple", "apricot"), (aGroup).toList())
        assertEquals(listOf("banana", "blueberry"), (bGroup).toList())
    }
}
