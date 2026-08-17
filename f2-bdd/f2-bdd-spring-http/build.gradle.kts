plugins {
	alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
	alias(catalogue.plugins.kotlin.spring)
	alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
	api(project(":f2-bdd:f2-bdd-spring-lambda"))
	api(project(":f2-client:f2-client-ktor"))
	api(project(":f2-spring:exception:f2-spring-boot-exception-http"))

	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.serialization.core)
}
