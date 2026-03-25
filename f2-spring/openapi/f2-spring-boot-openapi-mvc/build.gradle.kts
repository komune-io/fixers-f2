plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
}

dependencies {
    api(project(":f2-spring:openapi:f2-spring-boot-openapi"))
    api(libs.springdoc.openapi.ui)
}
