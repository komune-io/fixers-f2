plugins {
    kotlin("multiplatform")
    id("lt.petuska.npm.publish")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":f2-client"))
                api(project(":f2-dsl:f2-dsl-cqrs"))

                api("io.ktor:ktor-client-core:${Versions.ktor}")
                api("io.ktor:ktor-client-serialization:${Versions.ktor}")

            }
        }
        jsMain {
            dependencies {
                implementation("io.ktor:ktor-client-core-js:${Versions.ktor}")
                implementation("io.ktor:ktor-client-json-js:${Versions.ktor}")
            }
        }
        jvmMain {
            dependencies {
                implementation("io.ktor:ktor-client-core-jvm:${Versions.ktor}")
                implementation("io.ktor:ktor-client-cio:${Versions.ktor}"){
                    exclude("org.jetbrains.kotlin", "kotlin-reflect")
                }
                implementation("io.ktor:ktor-client-jackson:${Versions.ktor}"){
                    exclude("org.jetbrains.kotlin", "kotlin-reflect")
                }
            }
        }
    }
}


apply(from = rootProject.file("gradle/publishing-mpp.gradle"))
