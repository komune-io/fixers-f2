plugins {
    alias(libs.plugins.fixers.gradle.kotlin.mpp)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.kotlinx.serialization.core)

    jvmMainImplementation(libs.bundles.spring.data.commons)

    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)
    commonTestImplementation(libs.kotlinx.serialization.json)

    jvmTestImplementation(libs.bundles.test.junit)
    jvmTestImplementation(libs.jackson.module.kotlin)
}
