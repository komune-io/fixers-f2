plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))

    api(libs.spring.boot.autoconfigure)
}
