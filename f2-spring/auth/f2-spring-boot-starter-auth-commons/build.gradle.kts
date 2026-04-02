plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)
}
