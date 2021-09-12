package f2.feature.version

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DefaultFunctionConfig {

	@Autowired
	lateinit var buildProperties: BuildProperties


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
