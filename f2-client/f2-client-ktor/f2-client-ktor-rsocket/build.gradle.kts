import city.smartb.gradle.dependencies.FixersVersions

plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	id("city.smartb.fixers.gradle.publish")
	id("lt.petuska.npm.publish")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				api(project(":f2-client"))
				api(project(":f2-dsl:f2-dsl-cqrs"))
				implementation("io.rsocket.kotlin:rsocket-core:${Versions.rsocket}")
				implementation("io.rsocket.kotlin:rsocket-transport-ktor:${Versions.rsocket}")
				implementation("io.rsocket.kotlin:rsocket-transport-ktor-client:${Versions.rsocket}")
				api("io.ktor:ktor-client-core:${FixersVersions.Kotlin.ktor}")
			}
		}
		jsMain {
			dependencies {
				implementation("io.ktor:ktor-client-js:${FixersVersions.Kotlin.ktor}")
			}
		}
		jvmMain {
			dependencies {
				implementation("io.ktor:ktor-client-cio:${FixersVersions.Kotlin.ktor}") {
					exclude("org.jetbrains.kotlin", "kotlin-reflect")
				}
			}
		}
	}
}
