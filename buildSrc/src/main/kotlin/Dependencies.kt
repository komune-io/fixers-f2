import org.gradle.kotlin.dsl.DependencyHandlerScope

object PluginVersions {
	const val kotlin = "1.4.30"
	const val dokka = "1.4.20"
	const val springBoot = "2.4.1"
	const val springPom = "1.0.11.RELEASE"
	const val springCloudPom = "2020.0.1"

	const val npmPublish = "1.0.4"
	const val sonarQube = "3.0"

}

object Versions {
	const val coroutines = "1.4.2"
	const val kserialization = "1.0.1"
	const val ktor = "1.4.3"
	const val rsocket = "0.12.0"
	const val testcontainers = "1.15.1"
	const val reactor = "3.4.2"

	const val assertj = "3.15.0"

	const val ssm = "0.1.0-SNAPSHOT"
	const val vc = "0.1.0-SNAPSHOT"
	const val iris = "0.1.0-SNAPSHOT"
	const val f2 = "0.1.0-SNAPSHOT"
}


object Dependencies {
	object jvm {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}"
		)
	}

	object common {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
		)
		val kserialization = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kserialization}",
			"org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kserialization}"
		)
	}
}