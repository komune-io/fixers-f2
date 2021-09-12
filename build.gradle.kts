import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.jetbrains.kotlin.kapt") version PluginVersions.kotlin apply (false)
	kotlin("plugin.spring") version PluginVersions.kotlin apply false
	kotlin("plugin.serialization") version PluginVersions.kotlin
	id("org.springframework.boot") version PluginVersions.springBoot apply false

	id("lt.petuska.npm.publish") version PluginVersions.npmPublish apply false
	id("com.moowork.node") version "1.3.1"

	id("city.smartb.fixers.gradle.config") version PluginVersions.fixers
	id("city.smartb.fixers.gradle.sonar") version PluginVersions.fixers
	id("city.smartb.fixers.gradle.d2") version PluginVersions.fixers

}

allprojects {
	group = "city.smartb.f2"
	version = System.getenv("VERSION") ?: "latest"
	repositories {
		mavenCentral()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
		//For iris-sign
		maven { url = uri("https://jitpack.io") }
	}
}

subprojects {
	plugins.withType(lt.petuska.npm.publish.NpmPublishPlugin::class.java).whenPluginAdded {
		the<lt.petuska.npm.publish.dsl.NpmPublishExtension>().apply {
			organization = "smartb"
			repositories {
				repository("npmjs") {
					registry = uri("https://registry.npmjs.org")
					authToken = System.getenv("NPM_TOKEN")
				}
			}
			publications {
				publication("js") {
					packageJson {
						bundledDependencies("kotlin") { // Always includes "kotlin" dependency and filters out the rest by the spec
							-"ktor-ktor.*".toRegex() // Exclude "kotlin-test" dependency
						}
					}
				}
			}

		}
	}
}

tasks {
	val storybookDir = "${project.rootDir}/storybook"
	create<com.moowork.gradle.node.yarn.YarnTask>("installYarn") {
//		dependsOn("build")
		args = listOf("--cwd", storybookDir,"install")
	}

	create<com.moowork.gradle.node.yarn.YarnTask>("storybook") {
		dependsOn("yarn_install")
		args = listOf("--cwd", storybookDir, "storybook")

	}
}
