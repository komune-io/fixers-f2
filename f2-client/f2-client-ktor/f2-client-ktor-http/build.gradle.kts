plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("dev.petuska.npm.publish")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":f2-client:f2-client-core"))
                api(project(":f2-dsl:f2-dsl-cqrs"))

                api("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")
                api("io.ktor:ktor-client-serialization:${Versions.Kotlin.ktor}")
                implementation("org.jetbrains.kotlin:kotlin-test:${PluginVersions.kotlin}")


            }
        }
        commonTest {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test:${PluginVersions.kotlin}")
            }
        }

        jsMain {
            dependencies {
                implementation("io.ktor:ktor-client-core-js:${Versions.Kotlin.ktor}")
                implementation("io.ktor:ktor-client-json-js:${Versions.Kotlin.ktor}")
            }
        }
        jvmTest {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test:${PluginVersions.kotlin}")
            }
        }
        jvmMain {
            dependencies {
                implementation("io.ktor:ktor-client-core-jvm:${Versions.Kotlin.ktor}")
                implementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}"){
                    exclude("org.jetbrains.kotlin", "kotlin-reflect")
                }
                implementation("io.ktor:ktor-client-jackson:${Versions.Kotlin.ktor}"){
                    exclude("org.jetbrains.kotlin", "kotlin-reflect")
                }
            }
        }
    }
}
