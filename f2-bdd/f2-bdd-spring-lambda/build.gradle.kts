plugins {
	alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
	alias(catalogue.plugins.kotlin.spring)
	alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
	api(project(":f2-bdd:f2-bdd-spring-autoconfigure"))
	api(project(":f2-dsl:f2-dsl-function"))
	api(project(":f2-dsl:f2-dsl-cqrs"))
	api(libs.bundles.spring.cloud.function)
	api(libs.bundles.spring.test)
}
