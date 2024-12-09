package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.Envelope
import f2.dsl.fnc.F2Function
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OperatorsTest {

    @Test
    fun `test chunk, flattenConcurrently, groupBy, mapToEnvelope, pairEnvelope integration`() = runTest {
        // Initial flow of integers
        // Data: 1, 2, 3, 4, 5, 6, 7, 8, 9
        val initialFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9)


        // Chunk the flow into lists of 3 elements
        // Data: [1, 2, 3], [4, 5, 6], [7, 8, 9]
        val chunkedFlow: Flow<List<Int>> = initialFlow.chunk(size = 3) { it }


        // Flatten the chunked flow concurrently
        // Data: 1, 2, 3, 4, 5, 6, 7, 8, 9
        val flattenedFlow: Flow<Int> = chunkedFlow.flattenConcurrently()


        // Group the flattened flow by even and odd numbers
        // Data: (0, Flow of [2, 4, 6, 8]), (1, Flow of [1, 3, 5, 7, 9])
        val groupedFlow: Flow<Pair<Int, Flow<Int>>> = flattenedFlow.groupBy { it % 2 }


        // Define a simple F2Function to convert integers to strings
        // Data: 1 -> "1", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9"
        val function: F2Function<Int, String> = F2Function { flow -> flow.map { it.toString() } }


        // Map the grouped flow to envelopes
        // Data: (0, Envelope(id="2", data="2")), (0, Envelope(id="4", data="4")), ...
        val envelopedFlow: Flow<Pair<Int, Envelope<String>>> = groupedFlow.flatMapConcat { (key, flow) ->
            function.mapToEnvelope { it.toString() }.invoke(flow).map { key to it }
        }


        // Pair the envelopes with their original data
        // Data: (0, (2, "2")), (0, (4, "4")), ...
        val pairedFlow: Flow<Pair<Int, Pair<Int, String>>> = envelopedFlow.flatMapConcat { (key, envelope) ->
            flowOf(envelope).pairEnvelope { flowOf(envelope) }.map { key to (envelope.data.toInt() to envelope.data) }
        }


        // Collect the results
        // Data: [(0, (2, "2")), (0, (4, "4")), ...]
        val result: List<Pair<Int, Pair<Int, String>>> = pairedFlow.toList()


        // Assertions
        assertThat(result).hasSize(9)
        assertThat(result.map { it.second.first }).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9)
        assertThat(result.map { it.second.second }).containsExactlyInAnyOrder("1", "2", "3", "4", "5", "6", "7", "8", "9")
    }
}