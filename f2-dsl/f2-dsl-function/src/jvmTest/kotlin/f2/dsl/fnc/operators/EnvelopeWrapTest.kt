package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.Envelope
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EnvelopeWrapTest {

    @Test
    suspend fun `wrap message in envelope`() {
        val myFunction: F2Function<Letter, String> = f2Function { msg ->
            msg.content
        }

        // Create a Flow of Envelope<MyMessage>
        val envelopes = flowOf(
            Letter("Hello"),
            Letter("World")
        )

        // Invoke the wrapped function with the Flow of envelopes
        val results: Flow<Envelope<String>> = myFunction.mapToEnvelope().invoke(envelopes)
        val collectedResults = results.toList()

        // Assert the results using AssertJ
        assertThat(collectedResults).hasSize(2)

        assertThat(collectedResults[0].data).isEqualTo("Hello")
        assertThat(collectedResults[0].type).isEqualTo("String")

        assertThat(collectedResults[1].data).isEqualTo("World")
        assertThat(collectedResults[1].type).isEqualTo("String")
    }
}

class Letter(val content: String)
