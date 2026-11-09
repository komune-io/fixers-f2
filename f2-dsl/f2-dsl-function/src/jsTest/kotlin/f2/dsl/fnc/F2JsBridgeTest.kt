package f2.dsl.fnc

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import f2.dsl.fnc.exported.invokeF2Consumer as jsInvokeF2Consumer
import f2.dsl.fnc.exported.invokeF2Function as jsInvokeF2Function
import f2.dsl.fnc.exported.invokeF2Supplier as jsInvokeF2Supplier
import f2.dsl.fnc.exported.invokeF2SupplierSingle as jsInvokeF2SupplierSingle

class F2JsBridgeTest {

	private fun isJsArray(value: dynamic): Boolean = js("Array").isArray(value) as Boolean

	@Test
	fun invokeF2FunctionMapsANativeArrayIntoANativeArray() = runTest {
		val double = F2Function<Int, Int> { msgs: Flow<Int> -> msgs.map { it * 2 } }
		val result = jsInvokeF2Function(double, js("[1, 2]")).await()
		assertTrue(isJsArray(result))
		assertEquals("3,5", result.map { x: Int -> x + 1 }.join(",") as String)
	}

	@Test
	fun invokeF2ConsumerReceivesEveryMessageOfANativeArray() = runTest {
		val seen = mutableListOf<String>()
		val consumer = F2Consumer<String> { msgs -> msgs.collect { seen.add(it) } }
		jsInvokeF2Consumer(consumer, js("['a', 'b']")).await()
		assertEquals(listOf("a", "b"), seen)
	}

	@Test
	fun invokeF2SupplierCollectsIntoANativeArray() = runTest {
		val result = jsInvokeF2Supplier(F2Supplier { flowOf("x", "y") }).await()
		assertTrue(isJsArray(result))
		assertEquals("x-y", result.join("-") as String)
	}

	@Test
	fun invokeF2SupplierSingleResolvesToItsValue() = runTest {
		assertEquals(3, jsInvokeF2SupplierSingle(F2SupplierSingle { 3 }).await() as Int)
	}
}
