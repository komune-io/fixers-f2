package f2.bdd.spring.autoconfigure

import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner

class ApplicationContextRunnerBuilder {

	fun buildContext(config: Map<String, String> = emptyMap()): ReactiveWebApplicationContextRunner {
		return ReactiveWebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(ApplicationContextBuilder.SimpleConfiguration::class.java))
			.withConfig(config)
	}

	private fun ReactiveWebApplicationContextRunner.withConfig(config: Map<String, String>): ReactiveWebApplicationContextRunner {
		return withPropertyValues(
			*config.map { pair ->
				"${pair.key}=${pair.value}"
			}.toTypedArray()
		)
	}
}
