package f2.bdd.spring.autoconfigure

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext


class ApplicationContextBuilder {

	fun create(
		types: Array<Class<*>>, profile: Array<String> =
			emptyArray(), config: Map<String, String> = emptyMap()
	): GenericApplicationContext {
		return SpringApplicationBuilder(*types)
			.profiles(*profile)
			.withCouchdbConfig(config)
			.run() as GenericApplicationContext
	}

	private fun SpringApplicationBuilder.withCouchdbConfig(config: Map<String, String>): SpringApplicationBuilder {
		return properties(
			*config.map { pair ->
				"${pair.key}=${pair.value}"
			}.toTypedArray()
		)
	}

	@EnableAutoConfiguration
	@Configuration
	@ComponentScan("f2")
	class SimpleConfiguration


}
