plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))

    api("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")
}

apply(from = rootProject.file("gradle/publishing.gradle"))