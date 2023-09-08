plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("city.smartb.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi("io.ktor:ktor-utils:${Versions.Kotlin.ktor}")
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}
