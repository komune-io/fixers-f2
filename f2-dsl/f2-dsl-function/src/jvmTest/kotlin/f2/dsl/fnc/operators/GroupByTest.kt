package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GroupByTest {

    @Test
    fun `test groupBy with integers`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5, 6)
        val result = flow.groupBy { it % 2 }.toList()
        val evenGroup = result.first { it.first == 0 }.second.toList()
        val oddGroup = result.first { it.first == 1 }.second.toList()
        assertThat(evenGroup).containsExactly(2, 4, 6)
        assertThat(oddGroup).containsExactly(1, 3, 5)
    }

    @Test
    fun `test groupBy with strings`() = runTest {
        val flow = flowOf("apple", "banana", "apricot", "blueberry")
        val result = flow.groupBy { it.first() }.toList()
        val aGroup = result.first { it.first == 'a' }.second.toList()
        val bGroup = result.first { it.first == 'b' }.second.toList()
        assertThat(aGroup).containsExactly("apple", "apricot")
        assertThat(bGroup).containsExactly("banana", "blueberry")
    }
}