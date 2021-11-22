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

                api("io.ktor:ktor-client-core:${FixersVersions.Kotlin.ktor}")
                api("io.ktor:ktor-client-serialization:${FixersVersions.Kotlin.ktor}")

            }
        }
        jsMain {
            dependencies {
                implementation("io.ktor:ktor-client-core-js:${FixersVersions.Kotlin.ktor}")
                implementation("io.ktor:ktor-client-json-js:${FixersVersions.Kotlin.ktor}")
            }
        }
        jvmMain {
            dependencies {
                implementation("io.ktor:ktor-client-core-jvm:${FixersVersions.Kotlin.ktor}")
                implementation("io.ktor:ktor-client-cio:${FixersVersions.Kotlin.ktor}"){
                    exclude("org.jetbrains.kotlin", "kotlin-reflect")
                }
                implementation("io.ktor:ktor-client-jackson:${FixersVersions.Kotlin.ktor}"){
                    exclude("org.jetbrains.kotlin", "kotlin-reflect")
                }
            }
        }
    }
}
