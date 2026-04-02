plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
}

dependencies {
    api(project(":f2-spring:openapi:f2-spring-boot-openapi"))
    api(libs.springdoc.openapi.ui)
}
