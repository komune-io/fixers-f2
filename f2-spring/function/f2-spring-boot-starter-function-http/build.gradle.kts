plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api("org.springframework.cloud:spring-cloud-starter-function-webflux")
}

apply(from = rootProject.file("gradle/publishing.gradle"))