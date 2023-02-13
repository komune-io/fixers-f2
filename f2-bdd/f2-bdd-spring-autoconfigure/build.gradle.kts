plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	api(project(":f2-bdd:f2-bdd-config"))
	api(project(":f2-dsl:f2-dsl-function"))
	Dependencies.Jvm.Spring.cloudFunction(::api)
	Dependencies.Jvm.Test.springTest(::api)
}
