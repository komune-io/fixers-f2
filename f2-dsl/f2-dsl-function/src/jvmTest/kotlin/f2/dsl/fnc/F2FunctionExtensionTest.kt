package f2.dsl.fnc

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class F2FunctionExtensionTest {

    @Test
    suspend fun `invoke F2Function with single item`() {
        val function = F2Function<Int, String> { flow -> flow.map { "result-$it" } }

        val result = function.invoke(42)

        assertThat(result).isEqualTo("result-42")
    }

    @Test
    suspend fun `invokeWith invokes function with receiver as input`() {
        val function = F2Function<String, Int> { flow -> flow.map { it.length } }

        val result = "hello".invokeWith(function)

        assertThat(result).isEqualTo(5)
    }

    @Test
    suspend fun `f2Function creates F2Function from suspend function`() {
        val function = f2Function<Int, String> { "value-$it" }

        val result = function.invoke(flowOf(1, 2, 3)).toList()

        assertThat(result).containsExactly("value-1", "value-2", "value-3")
    }

    @Test
    suspend fun `f2SupplierSingle creates F2SupplierSingle from suspend function`() {
        val supplier = f2SupplierSingle { "supplied-value" }

        val result = supplier.invoke()

        assertThat(result).isEqualTo("supplied-value")
    }

    @Test
    suspend fun `f2Supplier creates F2Supplier from suspend function returning Flow`() {
        val supplier = f2Supplier { flowOf("a", "b", "c") }

        val result = supplier.invoke().toList()

        assertThat(result).containsExactly("a", "b", "c")
    }

    @Test
    suspend fun `asF2Supplier converts Iterable to F2Supplier`() {
        val list = listOf(1, 2, 3)

        val supplier = list.asF2Supplier()
        val result = supplier.invoke().toList()

        assertThat(result).containsExactly(1, 2, 3)
    }

    @Test
    suspend fun `f2Consumer creates F2Consumer from suspend function`() {
        val consumed = mutableListOf<Int>()
        val consumer = f2Consumer<Int> { consumed.add(it) }

        consumer.invoke(flowOf(1, 2, 3))

        assertThat(consumed).containsExactly(1, 2, 3)
    }

    @Test
    suspend fun `f2Function handles empty flow`() {
        val function = f2Function<Int, String> { "value-$it" }

        val result = function.invoke(flowOf()).toList()

        assertThat(result).isEmpty()
    }

    @Test
    suspend fun `f2Consumer handles empty flow`() {
        val consumed = mutableListOf<Int>()
        val consumer = f2Consumer<Int> { consumed.add(it) }

        consumer.invoke(flowOf())

        assertThat(consumed).isEmpty()
    }

    @Test
    suspend fun `asF2Supplier with empty list`() {
        val emptyList = emptyList<String>()

        val supplier = emptyList.asF2Supplier()
        val result = supplier.invoke().toList()

        assertThat(result).isEmpty()
    }
}
