plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
	kotlin("plugin.serialization")
}

dependencies {
	api(project(":f2-bdd:f2-bdd-spring-autoconfigure"))
	api(project(":f2-dsl:f2-dsl-function"))
	api(project(":f2-dsl:f2-dsl-cqrs"))
	Dependencies.Jvm.Spring.cloudFunction(::api)
	Dependencies.Jvm.Test.springTest(::api)
}
