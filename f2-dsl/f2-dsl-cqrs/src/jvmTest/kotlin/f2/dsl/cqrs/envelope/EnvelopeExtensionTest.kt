package f2.dsl.cqrs.envelope

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EnvelopeExtensionTest {

    @Test
    fun `asEnvelopeWithType wraps data with the given type`() {
        val envelope = "payload".asEnvelopeWithType(type = "CustomType", id = "id-1")

        assertThat(envelope.data).isEqualTo("payload")
        assertThat(envelope.type).isEqualTo("CustomType")
        assertThat(envelope.id).isEqualTo("id-1")
        assertThat(envelope.specversion).isEqualTo("1.0")
        assertThat(envelope.source).isNull()
        assertThat(envelope.time).isNull()
        assertThat(envelope.datacontenttype).isNull()
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

        assertThat(envelope.id).isEqualTo("origin-id")
        assertThat(envelope.source).isEqualTo("test-source")
        assertThat(envelope.time).isEqualTo("2024-01-01T00:00:00Z")
        assertThat(envelope.datacontenttype).isEqualTo("application/json")
    }

    @Test
    fun `asEnvelope wraps data with the class simple name as type`() {
        val envelope = "payload".asEnvelope(id = "id-2")

        assertThat(envelope.type).isEqualTo("String")
        assertThat(envelope.data).isEqualTo("payload")
        assertThat(envelope.id).isEqualTo("id-2")
    }

    @Test
    fun `asEnvelope generates a random id when none provided`() {
        val envelope = 42.asEnvelope()

        assertThat(envelope.id).isNotBlank()
        assertThat(envelope.type).isEqualTo("Int")
    }
}
