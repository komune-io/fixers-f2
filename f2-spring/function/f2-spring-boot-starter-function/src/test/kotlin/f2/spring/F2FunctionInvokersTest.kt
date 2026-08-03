package f2.spring

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reactor.core.publisher.Flux

class F2FunctionInvokersTest {

    private val function = F2Function<Any, Any> { flow ->
        flow.map { (it as String).uppercase() }
    }

    @Test
    fun `isValidFlowFunction accepts F2Function with Flux argument`() {
        assertTrue(isValidFlowFunction(function, Flux.just("a")))
    }

    @Test
    fun `isValidFlowFunction rejects non F2Function target`() {
        assertFalse(isValidFlowFunction("not a function", Flux.just("a")))
    }

    @Test
    fun `isValidFlowFunction rejects non Flux argument`() {
        assertFalse(isValidFlowFunction(function, "not a flux"))
    }

    @Test
    fun `invokeFlowFunction bridges Flux through the F2Function`() {
        val result = invokeFlowFunction(function, Flux.just("hello", "world")).collectList().block()

        assertEquals(listOf("HELLO", "WORLD"), result)
    }

    @Test
    fun `invokeFlowFunction rejects non F2Function target`() {
        val exception = assertThrows<IllegalArgumentException> {
            invokeFlowFunction("not a function", Flux.just("a"))
        }

        assertTrue(exception.message!!.contains("must be an F2Function"))
    }

    @Test
    fun `invokeFlowFunction rejects non Flux argument`() {
        val exception = assertThrows<IllegalArgumentException> {
            invokeFlowFunction(function, "not a flux")
        }

        assertTrue(exception.message!!.contains("must be a Flux"))
    }

    private val supplier = F2Supplier<Any> { flowOf("value") }

    @Test
    fun `isValidFlowSupplier accepts F2Supplier`() {
        assertTrue(isValidFlowSupplier(supplier))
    }

    @Test
    fun `isValidFlowSupplier rejects non F2Supplier target`() {
        assertFalse(isValidFlowSupplier("not a supplier"))
    }

    @Test
    fun `invokeFlowSupplier bridges the supplied Flow to a Flux`() {
        val result = invokeFlowSupplier(supplier).collectList().block()

        assertEquals(listOf("value"), result)
    }

    @Test
    fun `invokeFlowSupplier rejects non F2Supplier target`() {
        val exception = assertThrows<IllegalArgumentException> {
            invokeFlowSupplier("not a supplier")
        }

        assertTrue(exception.message!!.contains("must be an F2Supplier"))
    }
}
