plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons"))

    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)

    implementation(libs.spring.boot.autoconfigure)
}
