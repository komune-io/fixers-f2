plugins {
    alias(libs.plugins.fixers.gradle.kotlin.mpp)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.ktor.utils)
    commonMainApi(project(":f2-dsl:f2-dsl-function"))

    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)
}
