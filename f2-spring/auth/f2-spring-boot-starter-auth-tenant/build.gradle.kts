
plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    kapt(libs.spring.boot.configuration.processor)
    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)
}


