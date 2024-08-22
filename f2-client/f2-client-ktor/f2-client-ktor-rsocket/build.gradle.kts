plugins {
	id("io.komune.fixers.gradle.kotlin.mpp")
	id("io.komune.fixers.gradle.publish")
	//id("io.komune.fixers.gradle.npm")
	kotlin("plugin.serialization")
}
dependencies {
	commonMainApi(project(":f2-client:f2-client-core"))
	commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
	commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-common"))

	Dependencies.Mpp.Ktor.clientRsocket(::commonMainApi)
	Dependencies.Mpp.Ktor.client(::commonMainApi, ::jvmMainApi, ::jsMainApi)
}
