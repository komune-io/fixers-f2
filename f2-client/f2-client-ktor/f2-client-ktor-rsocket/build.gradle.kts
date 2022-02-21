plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	id("city.smartb.fixers.gradle.publish")
	id("lt.petuska.npm.publish")
}

kotlin {
	sourceSets {
		commonMain {
			dependencies {
				api(project(":f2-client:f2-client-core"))
				api(project(":f2-dsl:f2-dsl-cqrs"))
				implementation("io.rsocket.kotlin:rsocket-core:${Versions.rsocket}")
				implementation("io.rsocket.kotlin:rsocket-transport-ktor:${Versions.rsocket}")
				implementation("io.rsocket.kotlin:rsocket-transport-ktor-client:${Versions.rsocket}")
				api("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")
			}
		}
		jsMain {
			dependencies {
				implementation("io.ktor:ktor-client-js:${Versions.Kotlin.ktor}")
			}
		}
		jvmMain {
			dependencies {
				implementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}") {
					exclude("org.jetbrains.kotlin", "kotlin-reflect")
				}
			}
		}
	}
}
