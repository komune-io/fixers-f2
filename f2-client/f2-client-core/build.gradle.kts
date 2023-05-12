plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    commonMainApi("io.ktor:ktor-utils:${Versions.Kotlin.ktor}")
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}