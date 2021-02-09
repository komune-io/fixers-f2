plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    api("org.springframework.cloud:spring-cloud-function-context")
    api("org.springframework.cloud:spring-cloud-function-kotlin")
    api("org.springframework.boot:spring-boot-autoconfigure")
}

apply(from = rootProject.file("gradle/publishing.gradle"))