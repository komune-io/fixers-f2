plugins {
	id("io.komune.fixers.gradle.kotlin.mpp")
	id("io.komune.fixers.gradle.publish")
	//id("io.komune.fixers.gradle.npm")
	kotlin("plugin.serialization")
}
dependencies {
	commonMainApi(project(":f2-client:f2-client-core"))
	commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

	Dependencies.Mpp.rsocketKtorClient(::commonMainApi)

	//jsMainApi("io.ktor:ktor-client-js:${Versions.Kotlin.ktor}")
	jvmMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")
}
