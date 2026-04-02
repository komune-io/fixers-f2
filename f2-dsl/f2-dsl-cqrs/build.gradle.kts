plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    jvmMainImplementation(libs.bundles.spring.data.commons)

    jvmTestImplementation(libs.bundles.test.junit)
}
