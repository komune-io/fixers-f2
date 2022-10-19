plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    commonMainApi("io.ktor:ktor-utils:2.1.2")
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}