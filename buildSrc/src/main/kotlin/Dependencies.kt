import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersDependencies
import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add

object PluginVersions {
	const val fixers = "experimental-SNAPSHOT"
	const val d2 = "experimental-SNAPSHOT"
	const val kotlin = FixersPluginVersions.kotlin
	const val springBoot = FixersPluginVersions.springBoot
	const val npmPublish = FixersPluginVersions.npmPublish
}

object Versions {
	object Kotlin {
		const val ktor = FixersVersions.Kotlin.ktor
	}
	object Spring {
		const val function = FixersVersions.Spring.function
		const val boot = FixersVersions.Spring.boot
		const val data = FixersVersions.Spring.data
	}

	const val springFramework = "5.3.14"
	const val springDataCommons = "2.6.0"
	const val jacksonKotlin = "2.12.1"
	const val javaxPersistence = "2.2"
	const val rsocket = "0.14.3"
	const val embedMongo = "2.2.0"
	const val cucumber = "7.1.0"
}

object Dependencies {
	object Jvm {
		object Kotlin {
			fun coroutines(scope: Scope) = FixersDependencies.Jvm.Kotlin.coroutines(scope)
		}
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
