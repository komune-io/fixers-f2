object PluginVersions {
	const val kotlin = "1.5.21"
	const val dokka = "1.5.0"
	const val springBoot = "2.5.3"

	const val npmPublish = "1.0.4"
	const val sonarQube = "3.0"

}

object Versions {
	const val springBoot = PluginVersions.springBoot
	const val springFramework = "5.3.4"
	const val springCloudFunction = "3.1.3"
	const val springData = "2.4.5"

	const val jacksonKotlin = "2.12.1"
	const val javaxPersistence = "2.2"

	const val embedMongo = "2.2.0"

	const val coroutines = "1.5.1"
	const val kserialization = "1.2.2"
	const val ktor = "1.6.1"
	const val rsocket = "0.13.1"

	const val junit = "5.7.0"
	const val assertj = "3.15.0"

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