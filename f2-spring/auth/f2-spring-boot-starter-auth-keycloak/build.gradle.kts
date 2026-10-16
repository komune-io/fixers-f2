plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:auth:f2-spring-boot-starter-auth"))

    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.spring.boot.starter.webflux)
    testImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-webflux"))
}
