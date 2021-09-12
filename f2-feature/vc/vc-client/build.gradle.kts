plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    kotlin("plugin.serialization")
    id("lt.petuska.npm.publish")
}

dependencies {
    commonMainApi(project(":f2-feature:vc:vc-model"))

    commonMainApi(project(":f2-client:f2-client-ktor"))
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
}
