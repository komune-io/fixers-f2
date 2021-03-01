plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api("org.springframework.cloud:spring-cloud-starter-function-webflux:${Versions.springCloudFunction}")
}

apply(from = rootProject.file("gradle/publishing.gradle"))