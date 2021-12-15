import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersVersions
import org.gradle.api.artifacts.Dependency

typealias Scope = (dependencyNotation: Any) -> Dependency?

fun Scope.add(vararg deps: String): Scope {
	deps.forEach { this(it) }
	return this
}

object PluginVersions {
	const val fixers = FixersPluginVersions.fixers
	const val kotlin = FixersPluginVersions.kotlin
	const val springBoot = FixersPluginVersions.springBoot
	const val npmPublish = FixersPluginVersions.npmPublish
}


object Versions {
	const val springFramework = "5.3.13"
	const val jacksonKotlin = "2.12.1"
	const val javaxPersistence = "2.2"
	const val rsocket = "0.13.1"
	const val embedMongo = "2.2.0"
	const val cucumber = "7.1.0"
}

object Dependencies {
	object Jvm {
		fun cucumber(scope: Scope) = scope.add(
			"io.cucumber:cucumber-java8:${Versions.cucumber}",
			"io.cucumber:cucumber-junit-platform-engine:${Versions.cucumber}",
		)
		object Spring{
			fun cloudFunction(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-context:${FixersVersions.Spring.function}",
				"org.springframework.cloud:spring-cloud-function-kotlin:${FixersVersions.Spring.function}",
				"org.springframework.boot:spring-boot-autoconfigure:${FixersVersions.Spring.boot}",
				"com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}"
			)
			fun cloudFunctionWebflux(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-starter-function-webflux:${FixersVersions.Spring.function}"
			)
			fun cloudFunctionRSocket(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-rsocket:${FixersVersions.Spring.function}"
			)
		}
	}
}
