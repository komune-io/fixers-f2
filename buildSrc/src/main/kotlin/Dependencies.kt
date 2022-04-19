import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersDependencies
import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add

object PluginVersions {
	val fixers = FixersPluginVersions.fixers
	const val d2 = "0.3.1"
	const val kotlin = FixersPluginVersions.kotlin
	const val springBoot = FixersPluginVersions.springBoot
	const val npmPublish = FixersPluginVersions.npmPublish
}

object Versions {
	object Kotlin {
		const val ktor = FixersVersions.Kotlin.ktor
	}
	object Json {
		const val jackson = FixersVersions.Json.jacksonKotlin
	}
	object Spring {
		const val function = "3.2.3"
		const val boot = FixersVersions.Spring.boot
		const val data = FixersVersions.Spring.data
	}

	const val rsocket = "0.15.4"
	const val embedMongo = "2.2.0"
}

object Dependencies {
	object Jvm {
		object Kotlin {
			fun coroutines(scope: Scope) = FixersDependencies.Jvm.Kotlin.coroutines(scope)
		}
		object Json {
			fun jackson(scope: Scope) = FixersDependencies.Jvm.Json.jackson(scope)
		}

		fun cucumber(scope: Scope) = FixersDependencies.Jvm.Test.cucumber(scope)
		object Spring{
			fun dataCommons(scope: Scope) = FixersDependencies.Jvm.Spring.dataCommons(scope)

			fun cloudFunction(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-context:${Versions.Spring.function}",
				"org.springframework.cloud:spring-cloud-function-kotlin:${Versions.Spring.function}",
				"org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}",
				"com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.Json.jackson}"
			)
			fun cloudFunctionWebflux(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-starter-function-webflux:${Versions.Spring.function}"
			)
			fun cloudFunctionRSocket(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-rsocket:${Versions.Spring.function}"
			)
		}
	}
}
