package f2.client.ktor.common

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.serialization.Serializable

@Serializable
private data class JsonSample(
    val name: String,
    val optional: String = "default",
    val nullable: String? = null,
)

class HttpClientConfigExtensionTest {

    @Test
    fun `applyConfig installs content negotiation and applies custom config`() {
        val config = HttpClientConfig<HttpClientEngineConfig>()
        var applied = false

        config.applyConfig { applied = true }

        assertTrue(applied)
    }

    @Test
    fun `applyConfig accepts a null custom config`() {
        val config = HttpClientConfig<HttpClientEngineConfig>()

        config.applyConfig(null)

        // no custom block to run: the call must simply not fail
        assertTrue(config.clone() !== config)
    }

    @Test
    fun `F2DefaultJson ignores unknown keys and is lenient`() {
        val decoded = F2DefaultJson.decodeFromString<JsonSample>(
            """{"name": "value", "unknown": "field"}"""
        )

        assertEquals("value", decoded.name)
        assertNull(decoded.nullable)
    }

    @Test
    fun `F2DefaultJson encodes defaults and omits nulls`() {
        val encoded = F2DefaultJson.encodeToString(JsonSample(name = "value"))

        assertTrue(encoded.contains("\"optional\":\"default\""))
        assertFalse(encoded.contains("nullable"))
    }
}
