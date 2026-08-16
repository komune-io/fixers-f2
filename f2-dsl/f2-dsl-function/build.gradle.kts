
plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.kotlinx.serialization.core)

    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)

}
