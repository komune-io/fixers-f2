package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.asEnvelope
import f2.dsl.fnc.F2Function
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest

class MapToEnvelopeTest {

    @Test
    fun `test mapToEnvelopeWithRandomId on F2Function`() = runTest {
        val function = F2Function<Int, String> { flow -> flow.map { it.toString() } }
        val result = function.mapToEnvelopeWithRandomId().invoke(flowOf(1, 2, 3)).toList()
        assertEquals(3, (result).count())
        assertEquals(listOf("1", "2", "3"), (result.map { it.data }).toList())
    }

    @Test
    fun `test mapToEnvelope with custom ID`() = runTest {
        val function = F2Function<Int, String> { flow -> flow.map { it.toString() } }
        val result = function.mapToEnvelope { it.toString() }.invoke(flowOf(1, 2, 3)).toList()
        assertEquals(3, (result).count())
        assertEquals(listOf("1", "2", "3"), (result.map { it.id }).toList())
    }


    @Test
    fun `test mapToEnvelopeWithRandomId on Flow`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.mapToEnvelopeWithRandomId().toList()
        assertEquals(3, (result).count())
        result.forEach { envelope ->
            assertNotNull(envelope.id)
            assertTrue(envelope.data in listOf(1, 2, 3))
        }
    }

    @Test
    fun `test mapToEnvelope with custom ID function`() = runTest {
        val flow = flowOf("a", "b", "c")
        val result = flow.mapToEnvelope { it.uppercase() }.toList()
        assertEquals(3, (result).count())
        result.forEach { envelope ->
            assertTrue(envelope.id in listOf("A", "B", "C"))
            assertTrue(envelope.data in listOf("a", "b", "c"))
        }
    }

    @Test
    fun `test mapToEnvelope with type parameter`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.mapToEnvelope(type = "IntType") { "id-$it" }.toList()
        assertEquals(3, (result).count())
        result.forEach { envelope ->
            assertEquals("IntType", envelope.type)
        }
    }

    @Test
    fun `test mapEnvelope transforms envelope data`() = runTest {
        val envelope = 10.asEnvelope(id = "test-id")
        val result = envelope.mapEnvelope<Int, Int>(transform = { it * 2 })
        assertEquals("test-id", result.id)
        assertEquals(20, result.data)
    }

    @Test
    fun `test mapEnvelopeWithType transforms with explicit type`() = runTest {
        val envelope = 10.asEnvelope(id = "test-id")
        val result = envelope.mapEnvelopeWithType(
            transform = { it.toString() },
            type = "StringResult"
        )
        assertEquals("test-id", result.id)
        assertEquals("10", result.data)
        assertEquals("StringResult", result.type)
    }

    @Test
    fun `test mapEnvelopesReified transforms flow of envelopes`() = runTest {
        val flow = flowOf(
            1.asEnvelope(id = "1"),
            2.asEnvelope(id = "2")
        )
        val result = flow.mapEnvelopesReified<Int, Int> { it * 3 }.toList()
        assertEquals(2, (result).count())
        assertEquals(listOf(3, 6), (result.map { it.data }).toList())
    }

    @Test
    fun `test mapEnvelopes transforms with type`() = runTest {
        val flow = flowOf(
            1.asEnvelope(id = "1"),
            2.asEnvelope(id = "2")
        )
        val result = flow.mapEnvelopes(
            transform = { "value-$it" },
            type = "StringType"
        ).toList()
        assertEquals(2, (result).count())
        assertEquals(listOf("value-1", "value-2"), (result.map { it.data }).toList())
        assertTrue(result.all { it.type == "StringType" })
    }
}
