plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
	kotlin("plugin.spring")
}

dependencies {
	api(project(":f2-bdd:f2-bdd-config"))

	api("org.springframework.boot:spring-boot-starter-test:${city.smartb.gradle.dependencies.FixersVersions.Spring.boot}")
}