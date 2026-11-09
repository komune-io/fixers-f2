package f2.dsl.fnc

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest

class F2JsBridgeTest {

	@Test
	fun invokeF2FunctionMapsEachMessage() = runTest {
		val double = F2Function<Int, Int> { msgs: Flow<Int> -> msgs.map { it * 2 } }
		assertEquals(listOf(2, 4), invokeF2Function(double, listOf(1, 2)))
	}

	@Test
	fun invokeF2ConsumerReceivesEveryMessage() = runTest {
		val seen = mutableListOf<String>()
		val consumer = F2Consumer<String> { msgs -> msgs.collect { seen.add(it) } }
		invokeF2Consumer(consumer, listOf("a", "b"))
		assertEquals(listOf("a", "b"), seen)
	}

	@Test
	fun invokeF2SupplierCollectsItsValues() = runTest {
		assertEquals(listOf("x", "y"), invokeF2Supplier(F2Supplier { flowOf("x", "y") }))
	}

	@Test
	fun invokeF2SupplierSingleReturnsItsValue() = runTest {
		assertEquals(3, invokeF2SupplierSingle(F2SupplierSingle { 3 }))
	}
}
