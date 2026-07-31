package f2.feature.version

import java.util.Properties
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.info.BuildProperties

class DefaultFunctionConfigTest {

    private fun config(properties: Properties): DefaultFunctionConfig {
        return DefaultFunctionConfig().apply {
            buildProperties = BuildProperties(properties)
        }
    }

    @Test
    fun `version should return build version`() {
        val config = config(Properties().apply { setProperty("version", "1.2.3") })
        assertThat(config.version()()).isEqualTo("1.2.3")
    }

    @Test
    fun `version should default to dev when missing`() {
        val config = config(Properties())
        assertThat(config.version()()).isEqualTo("dev")
    }

    @Test
    fun `name should return build name`() {
        val config = config(Properties().apply { setProperty("name", "my-app") })
        assertThat(config.name()()).isEqualTo("my-app")
    }

    @Test
    fun `name should default to dev when missing`() {
        val config = config(Properties())
        assertThat(config.name()()).isEqualTo("dev")
    }
}
