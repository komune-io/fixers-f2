plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)
}
