package f2.spring

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DeclarationSuspendFunctionTest {

    @Test
    suspend fun `invokeFunction returns suspend function that delegates to F2Function`() {
        val f2Function = F2Function<Any, Any> { flow ->
            flow.map { (it as String).uppercase() }
        }

        val suspendFunction = invokeFunction(f2Function)
        val inputFlow = flowOf("hello", "world")
        val result = suspendFunction(inputFlow).toList()

        assertEquals(listOf("HELLO", "WORLD"), result)
    }

    @Test
    suspend fun `invokeFunction preserves flow transformation`() {
        val f2Function = F2Function<Any, Any> { flow ->
            flow.map { (it as Int) * 2 }
        }

        val suspendFunction = invokeFunction(f2Function)
        val inputFlow = flowOf(1, 2, 3)
        val result = suspendFunction(inputFlow).toList()

        assertEquals(listOf(2, 4, 6), result)
    }

    @Test
    suspend fun `invokeConsumer returns suspend function that delegates to F2Consumer`() {
        val consumed = mutableListOf<Any>()
        val f2Consumer = F2Consumer<Any> { flow ->
            flow.toList(consumed)
        }

        val suspendFunction = invokeConsumer(f2Consumer)
        val inputFlow = flowOf("a", "b", "c")
        suspendFunction(inputFlow)

        assertEquals(listOf("a", "b", "c"), consumed)
    }

    @Test
    suspend fun `invokeConsumer processes all flow elements`() {
        var sum = 0
        val f2Consumer = F2Consumer<Any> { flow ->
            flow.collect { sum += it as Int }
        }

        val suspendFunction = invokeConsumer(f2Consumer)
        val inputFlow = flowOf(1, 2, 3, 4, 5)
        suspendFunction(inputFlow)

        assertEquals(15, sum)
    }

    @Test
    suspend fun `invokeSupplier returns suspend function that delegates to F2Supplier`() {
        val f2Supplier = F2Supplier<Any> {
            flowOf("supplied1", "supplied2")
        }

        val suspendFunction = invokeSupplier(f2Supplier)
        val result = suspendFunction().toList()

        assertEquals(listOf("supplied1", "supplied2"), result)
    }

    @Test
    suspend fun `invokeSupplier returns flow from supplier`() {
        val f2Supplier = F2Supplier<Any> {
            flowOf(10, 20, 30)
        }

        val suspendFunction = invokeSupplier(f2Supplier)
        val result = suspendFunction().toList()

        assertEquals(listOf(10, 20, 30), result)
    }

    @Test
    suspend fun `invokeFunction handles empty flow`() {
        val f2Function = F2Function<Any, Any> { flow ->
            flow.map { it }
        }

        val suspendFunction = invokeFunction(f2Function)
        val inputFlow: Flow<Any> = flowOf()
        val result = suspendFunction(inputFlow).toList()

        assertEquals(emptyList<Any>(), result)
    }

    @Test
    suspend fun `invokeConsumer handles empty flow`() {
        var invoked = false
        val f2Consumer = F2Consumer<Any> { flow ->
            flow.collect { invoked = true }
        }

        val suspendFunction = invokeConsumer(f2Consumer)
        val inputFlow: Flow<Any> = flowOf()
        suspendFunction(inputFlow)

        assertEquals(false, invoked)
    }

    @Test
    suspend fun `invokeSupplier can return empty flow`() {
        val f2Supplier = F2Supplier<Any> {
            flowOf()
        }

        val suspendFunction = invokeSupplier(f2Supplier)
        val result = suspendFunction().toList()

        assertEquals(emptyList<Any>(), result)
    }
}
