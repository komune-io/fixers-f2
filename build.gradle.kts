plugins {
	id("org.jetbrains.kotlin.kapt") version PluginVersions.kotlin apply false
	kotlin("plugin.spring") version PluginVersions.kotlin apply false
	kotlin("plugin.serialization") version PluginVersions.kotlin
	id("org.springframework.boot") version PluginVersions.springBoot apply false
	id("city.smartb.fixers.gradle.npm") version PluginVersions.fixers  apply false

	id("com.moowork.node") version "1.3.1"

	id("city.smartb.fixers.gradle.config") version PluginVersions.fixers
	id("city.smartb.fixers.gradle.sonar") version PluginVersions.fixers
	id("city.smartb.fixers.gradle.d2") version PluginVersions.d2
}

allprojects {
	group = "city.smartb.f2"
	version = System.getenv("VERSION") ?: "local"
	repositories {
		defaultRepo()
	}
}

tasks {
	val storybookDir = "${project.rootDir}/storybook"
	create<com.moowork.gradle.node.yarn.YarnTask>("installYarn") {
		args = listOf("--cwd", storybookDir, "install")
	}

	create<com.moowork.gradle.node.yarn.YarnTask>("storybook") {
		dependsOn("yarn_install")
		args = listOf("--cwd", storybookDir, "storybook")
	}
}

fixers {
	npm {
		version = "0.17.0-SNAPSHOT"
	}
	d2 {
		outputDirectory = file("storybook/d2/")
	}
	bundle {
		id = "f2"
		name = "F2"
		description = "Wrapper around Spring Cloud Function"
		url = "https://gitlab.smartb.city/fixers/F2"
	}
}
