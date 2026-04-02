plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons"))

    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)

    implementation(libs.spring.boot.autoconfigure)
}
