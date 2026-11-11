plugins {
    alias(libs.plugins.fixers.gradle.kotlin.mpp)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.kotlinx.serialization.core)
}
