plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.spring)
    `java-test-fixtures`
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)
    api(project(":f2-spring:exception:f2-spring-boot-exception-http-webflux"))

    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.spring.boot.starter.webflux)
    testImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-webflux"))
}
