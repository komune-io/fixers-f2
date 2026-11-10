package f2.feature.version

import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DefaultFunctionConfig(
    private val buildProperties: BuildProperties,
) {

    @Bean
    fun version(): () -> String {
        return {
            buildProperties.version ?: "dev"
        }
    }

    @Bean
    fun name(): () -> String {
        return {
            buildProperties.name ?: "dev"
        }
    }
}
