plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

dependencies {

    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-webflux"))
    implementation(project(":f2-spring:openapi:f2-spring-boot-openapi-webflux"))

    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.bundles.spring.test)
}

