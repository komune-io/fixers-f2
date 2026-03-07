plugins {
    alias(libs.plugins.fixers.kotlin.mpp)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    jvmMainImplementation(libs.bundles.spring.data.commons)

    jvmTestImplementation(libs.bundles.test.junit)
}
