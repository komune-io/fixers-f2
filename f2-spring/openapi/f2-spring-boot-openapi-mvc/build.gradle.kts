plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
}

dependencies {
    api(project(":f2-spring:openapi:f2-spring-boot-openapi"))
    api(libs.springdoc.openapi.ui)
}
