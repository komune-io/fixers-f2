plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:exception:f2-spring-boot-exception-http"))
    api(libs.spring.boot.starter.web)
    implementation(libs.jackson.module.kotlin)
}
