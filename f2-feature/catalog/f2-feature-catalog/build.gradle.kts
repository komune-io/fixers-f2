plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(libs.bundles.spring.cloud.function)

    testImplementation(libs.bundles.spring.test)
}
