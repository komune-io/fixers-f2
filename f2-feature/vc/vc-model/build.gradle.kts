plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    kotlin("plugin.serialization")
    id("lt.petuska.npm.publish")
}

dependencies {
    jvmMainApi("com.fasterxml.jackson.core:jackson-annotations:2.12.4")
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}
