package f2.dsl.cqrs.envelope

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNull

class EnvelopeExtensionTest {

    @Test
    fun `asEnvelopeWithType wraps data with the given type`() {
        val envelope = "payload".asEnvelopeWithType(type = "CustomType", id = "id-1")

        assertEquals("payload", envelope.data)
        assertEquals("CustomType", envelope.type)
        assertEquals("id-1", envelope.id)
        assertEquals("1.0", envelope.specversion)
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

        assertEquals("origin-id", envelope.id)
        assertEquals("test-source", envelope.source)
        assertEquals("2024-01-01T00:00:00Z", envelope.time)
        assertEquals("application/json", envelope.datacontenttype)
    }

    @Test
    fun `asEnvelope wraps data with the class simple name as type`() {
        val envelope = "payload".asEnvelope(id = "id-2")

        assertEquals("String", envelope.type)
        assertEquals("payload", envelope.data)
        assertEquals("id-2", envelope.id)
    }

    @Test
    fun `asEnvelope generates a random id when none provided`() {
        val envelope = 42.asEnvelope()

        assertTrue(envelope.id.isNotBlank())
        assertEquals("Int", envelope.type)
    }
}
