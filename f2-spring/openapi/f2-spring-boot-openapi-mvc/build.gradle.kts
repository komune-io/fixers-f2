plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(libs.bundles.spring.cloud.function)
    api(libs.springdoc.openapi.ui)
}
