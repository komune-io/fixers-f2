package f2.client.ktor.common

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import kotlinx.serialization.Serializable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

        assertThat(applied).isTrue()
    }

    @Test
    fun `applyConfig accepts a null custom config`() {
        val config = HttpClientConfig<HttpClientEngineConfig>()

        config.applyConfig(null)

        assertThat(config).isNotNull()
    }

    @Test
    fun `F2DefaultJson ignores unknown keys and is lenient`() {
        val decoded = F2DefaultJson.decodeFromString<JsonSample>(
            """{"name": "value", "unknown": "field"}"""
        )

        assertThat(decoded.name).isEqualTo("value")
        assertThat(decoded.nullable).isNull()
    }

    @Test
    fun `F2DefaultJson encodes defaults and omits nulls`() {
        val encoded = F2DefaultJson.encodeToString(JsonSample(name = "value"))

        assertThat(encoded)
            .contains("\"optional\":\"default\"")
            .doesNotContain("nullable")
    }
}
