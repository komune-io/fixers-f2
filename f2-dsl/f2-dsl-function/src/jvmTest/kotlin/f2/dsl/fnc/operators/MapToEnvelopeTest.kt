package f2.dsl.fnc.operators

import f2.dsl.fnc.F2Function
import kotlinx.coroutines.flow.*
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
}