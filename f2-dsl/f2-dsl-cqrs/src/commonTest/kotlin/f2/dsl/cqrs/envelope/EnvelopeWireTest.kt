package f2.dsl.cqrs.envelope

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class EnvelopeWireTest {

    private val serializer = Envelope.serializer(String.serializer())
    private val withoutDefaults = Json { encodeDefaults = false }

    @Test
    fun `an envelope always writes its specversion, even when defaults are left out`() {
        val json = withoutDefaults.encodeToString(serializer, Envelope(id = "e-1", data = "payload", type = "Item"))

        assertTrue(actual = json.contains(""""specversion":"1.0""""), message = json)
    }

    @Test
    fun `an envelope written with its specversion is read back`() {
        val envelope = Json.decodeFromString(
            serializer,
            """{"id":"e-1","data":"payload","type":"Item","specversion":"1.0"}""",
        )

        assertEquals(expected = "1.0", actual = envelope.specversion)
        assertEquals(expected = "payload", actual = envelope.data)
    }
}
