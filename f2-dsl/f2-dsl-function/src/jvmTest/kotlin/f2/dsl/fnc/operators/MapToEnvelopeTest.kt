package f2.dsl.fnc.operators

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
        val result = flow.mapToEnvelope { it.toUpperCase() }.toList()
        assertThat(result).hasSize(3)
        result.forEach { envelope ->
            assertThat(envelope.id).isIn("A", "B", "C")
            assertThat(envelope.data).isIn("a", "b", "c")
        }
    }
}