plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    //id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    Dependencies.Mpp.Ktor.utils(::commonMainApi)
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}
