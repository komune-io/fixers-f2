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

    jvmTestImplementation(libs.bundles.test.junit)
}
