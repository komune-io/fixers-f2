object PluginVersions {
	const val kotlin = "1.5.32"
	const val fixers = "0.1.1"
	const val springBoot = "2.3.4.RELEASE"

	const val npmPublish = "1.0.4"
}

object Versions {
	const val springBoot = PluginVersions.springBoot
	const val springFramework = "5.2.9.RELEASE"
	const val springCloudFunction = "3.1.3"
	const val springData = PluginVersions.springBoot

	const val jacksonKotlin = "2.12.1"
	const val javaxPersistence = "2.2"

	const val embedMongo = "2.2.0"

	const val coroutines = "1.4.2"
	const val kserialization = "1.0.0"
	const val ktor = "1.4.1"
	const val rsocket = "0.13.1"

	const val junit = "5.7.0"
	const val assertj = "3.15.0"

}


object Dependencies {
	object jvm {
		val coroutines = arrayOf(
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutines}",
			"org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}"
		)
		val junit = arrayOf(
			"org.junit.jupiter:junit-jupiter:${Versions.junit}",
			"org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
			"org.assertj:assertj-core:${Versions.assertj}"
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
