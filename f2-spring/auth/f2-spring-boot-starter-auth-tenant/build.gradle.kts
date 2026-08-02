
plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
    alias(catalogue.plugins.kotlin.kapt)
}

dependencies {
    kapt(libs.spring.boot.configuration.processor)
    api(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons"))
    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)

    implementation(libs.reactor.core)

    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.spring.boot.starter.webflux)
}


