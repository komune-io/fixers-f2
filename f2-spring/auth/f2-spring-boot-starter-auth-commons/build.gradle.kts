plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
    `java-test-fixtures`
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)

    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.spring.boot.starter.webflux)
    testImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-webflux"))
}
