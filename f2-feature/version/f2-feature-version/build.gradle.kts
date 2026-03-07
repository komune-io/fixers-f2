plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))

    api(libs.spring.boot.autoconfigure)
}
