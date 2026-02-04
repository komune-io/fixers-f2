package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.Envelope
import f2.dsl.cqrs.envelope.asEnvelope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PairEnvelopeTest {

    @Test
    suspend fun `test pairEnvelope with simple transformation`() {
        val flow = flowOf(
            1.asEnvelope(id = "1"),
            2.asEnvelope(id = "2"),
            3.asEnvelope(id = "3"),
        )
        val result = flow.pairEnvelope { inputFlow ->
            inputFlow.map { envelope ->
                (envelope.data * 2).asEnvelope(id = envelope.id)
            }
        }.toList()
        assertThat(result).containsExactly(
            1 to 2,
            2 to 4,
            3 to 6
        )
    }

    @Test
    suspend fun `test pairEnvelope with chunking`() {
        val flow = flowOf(
            Envelope(id = "1", data = 1, type = 1::class.simpleName!!),
            Envelope(id = "2", data = 2, type = 2::class.simpleName!!),
            Envelope(id = "3", data = 3, type = 3::class.simpleName!!),
            Envelope(id = "4", data = 4, type = 4::class.simpleName!!)
        )
        val result = flow.pairEnvelope(chunk = 2) { inputFlow ->
            inputFlow.map { envelope ->
                Envelope(id = envelope.id, data = envelope.data * 2, Int::class.simpleName!!)
            }
        }.toList()
        assertThat(result).containsExactly(
            1 to 2,
            2 to 4,
            3 to 6,
            4 to 8
        )
    }
}
