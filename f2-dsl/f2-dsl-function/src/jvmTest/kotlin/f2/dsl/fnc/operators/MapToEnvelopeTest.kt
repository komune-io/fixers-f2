package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.asEnvelope
import f2.dsl.fnc.F2Function
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapToEnvelopeTest {

    @Test
    fun `test mapToEnvelope with default ID`() = runTest {
        val function = F2Function<Int, String> { flow -> flow.map { it.toString() } }
        val result = function.mapToEnvelope().invoke(flowOf(1, 2, 3)).toList()
        assertThat(result).hasSize(3)
        assertThat(result.map { it.data }).containsExactly("1", "2", "3")
    }

    @Test
    fun `test mapToEnvelope with custom ID`() = runTest {
        val function = F2Function<Int, String> { flow -> flow.map { it.toString() } }
        val result = function.mapToEnvelope { it.toString() }.invoke(flowOf(1, 2, 3)).toList()
        assertThat(result).hasSize(3)
        assertThat(result.map { it.id }).containsExactly("1", "2", "3")
    }


    @Test
    fun `test mapToEnvelope with default ID function`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.mapToEnvelope().toList()
        assertThat(result).hasSize(3)
        result.forEach { envelope ->
            assertThat(envelope.id).isNotNull()
            assertThat(envelope.data).isIn(1, 2, 3)
        }
    }

    @Test
    fun `test mapToEnvelope with custom ID function`() = runTest {
        val flow = flowOf("a", "b", "c")
        val result = flow.mapToEnvelope { it.uppercase() }.toList()
        assertThat(result).hasSize(3)
        result.forEach { envelope ->
            assertThat(envelope.id).isIn("A", "B", "C")
            assertThat(envelope.data).isIn("a", "b", "c")
        }
    }

    @Test
    fun `test mapToEnvelope with type parameter`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.mapToEnvelope(type = "IntType") { "id-$it" }.toList()
        assertThat(result).hasSize(3)
        result.forEach { envelope ->
            assertThat(envelope.type).isEqualTo("IntType")
        }
    }

    @Test
    fun `test mapEnvelope transforms envelope data`() = runTest {
        val envelope = 10.asEnvelope(id = "test-id")
        val result = envelope.mapEnvelope<Int, Int>(transform = { it * 2 })
        assertThat(result.id).isEqualTo("test-id")
        assertThat(result.data).isEqualTo(20)
    }

    @Test
    fun `test mapEnvelopeWithType transforms with explicit type`() = runTest {
        val envelope = 10.asEnvelope(id = "test-id")
        val result = envelope.mapEnvelopeWithType(
            transform = { it.toString() },
            type = "StringResult"
        )
        assertThat(result.id).isEqualTo("test-id")
        assertThat(result.data).isEqualTo("10")
        assertThat(result.type).isEqualTo("StringResult")
    }

    @Test
    fun `test mapEnvelopesReified transforms flow of envelopes`() = runTest {
        val flow = flowOf(
            1.asEnvelope(id = "1"),
            2.asEnvelope(id = "2")
        )
        val result = flow.mapEnvelopesReified<Int, Int> { it * 3 }.toList()
        assertThat(result).hasSize(2)
        assertThat(result.map { it.data }).containsExactly(3, 6)
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
        assertThat(result).hasSize(2)
        assertThat(result.map { it.data }).containsExactly("value-1", "value-2")
        assertThat(result.all { it.type == "StringType" }).isTrue()
    }
}