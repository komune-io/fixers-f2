plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:exception:f2-spring-boot-exception-http"))
    api(libs.spring.boot.starter.web)
    implementation(libs.jackson.module.kotlin)
}
