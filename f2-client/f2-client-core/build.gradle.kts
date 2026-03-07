plugins {
    alias(libs.plugins.fixers.kotlin.mpp)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.ktor.utils)
    commonMainApi(project(":f2-dsl:f2-dsl-function"))
}
