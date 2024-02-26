plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    //id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi("io.ktor:ktor-utils:${Versions.Kotlin.ktor}")
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}
