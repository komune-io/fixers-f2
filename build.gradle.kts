plugins {
	id("org.jetbrains.kotlin.kapt") version PluginVersions.kotlin apply false
	kotlin("plugin.spring") version PluginVersions.kotlin apply false
	kotlin("plugin.serialization") version PluginVersions.kotlin
	id("org.springframework.boot") version PluginVersions.springBoot apply false
	//id("io.komune.fixers.gradle.npm") version PluginVersions.fixers  apply false

	id("io.komune.fixers.gradle.config") version PluginVersions.fixers
	id("io.komune.fixers.gradle.check") version PluginVersions.fixers
//	id("io.komune.fixers.gradle.d2") version PluginVersions.d2
}

allprojects {
	group = "io.komune.f2"
	version = System.getenv("VERSION") ?: "experimental-SNAPSHOT"
	repositories {
		defaultRepo()
	}
}

fixers {
//	npm {
//		version = "0.22.0-SNAPSHOT"
//	}
//	d2 {
//		outputDirectory = file("storybook/d2/")
//	}
	bundle {
		id = "f2"
		name = "F2"
		description = "Wrapper around Spring Cloud Function"
		url = "https://github.com/komune-io/fixers-f2"
	}
	sonar {
		organization = "komune-io"
		projectKey = "komune-io_fixers-f2"
	}
}
