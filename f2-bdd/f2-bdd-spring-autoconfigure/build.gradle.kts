plugins {
	alias(libs.plugins.fixers.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
}

dependencies {
	api(project(":f2-bdd:f2-bdd-config"))
	api(project(":f2-dsl:f2-dsl-function"))
	api(libs.bundles.spring.cloud.function)
	api(libs.bundles.spring.test)
}
