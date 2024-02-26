plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    //id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi("io.ktor:ktor-client-logging:${Versions.Kotlin.ktor}")
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-http"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-rsocket"))
}
