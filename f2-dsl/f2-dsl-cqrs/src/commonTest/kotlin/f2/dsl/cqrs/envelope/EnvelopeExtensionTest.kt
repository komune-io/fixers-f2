package f2.dsl.cqrs.envelope

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNull

class EnvelopeExtensionTest {

    @Test
    fun `asEnvelopeWithType wraps data with the given type`() {
        val envelope = "payload".asEnvelopeWithType(type = "CustomType", id = "id-1")

        assertEquals(expected = "payload", actual = envelope.data)
        assertEquals(expected = "CustomType", actual = envelope.type)
        assertEquals(expected = "id-1", actual = envelope.id)
        assertEquals(expected = "1.0", actual = envelope.specversion)
        assertNull(envelope.source)
        assertNull(envelope.time)
        assertNull(envelope.datacontenttype)
    }

    @Test
    fun `asEnvelopeWithType copies metadata from source envelope`() {
        val from = Envelope(
            id = "origin-id",
            data = 1,
            type = "Origin",
            source = "test-source",
            time = "2024-01-01T00:00:00Z",
            datacontenttype = "application/json",
        )

        val envelope = "payload".asEnvelopeWithType(type = "CustomType", from = from)

        assertEquals(expected = "origin-id", actual = envelope.id)
        assertEquals(expected = "test-source", actual = envelope.source)
        assertEquals(expected = "2024-01-01T00:00:00Z", actual = envelope.time)
        assertEquals(expected = "application/json", actual = envelope.datacontenttype)
    }

    @Test
    fun `asEnvelope wraps data with the class simple name as type`() {
        val envelope = "payload".asEnvelope(id = "id-2")

        assertEquals(expected = "String", actual = envelope.type)
        assertEquals(expected = "payload", actual = envelope.data)
        assertEquals(expected = "id-2", actual = envelope.id)
    }

    @Test
    fun `asEnvelope generates a random id when none provided`() {
        val envelope = 42.asEnvelope()

        assertTrue(envelope.id.isNotBlank())
        assertEquals(expected = "Int", actual = envelope.type)
    }
}
