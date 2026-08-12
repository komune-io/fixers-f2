package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.Envelope
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest

class EnvelopeWrapTest {

    @Test
    fun `wrap message in envelope`() = runTest {
        val myFunction: F2Function<Letter, String> = f2Function { msg ->
            msg.content
        }

        // Create a Flow of Envelope<MyMessage>
        val envelopes = flowOf(
            Letter("Hello"),
            Letter("World")
        )

        // Invoke the wrapped function with the Flow of envelopes
        val results: Flow<Envelope<String>> = myFunction.mapToEnvelopeWithRandomId().invoke(envelopes)
        val collectedResults = results.toList()

        // Assert the results using AssertJ
        assertEquals(2, (collectedResults).count())

        assertEquals("Hello", collectedResults[0].data)
        assertEquals("String", collectedResults[0].type)

        assertEquals("World", collectedResults[1].data)
        assertEquals("String", collectedResults[1].type)
    }
}

class Letter(val content: String)
