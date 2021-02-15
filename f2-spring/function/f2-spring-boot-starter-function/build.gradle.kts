plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
}

dependencies {
    api(project(":f2-dsl:f2-dsl-function"))
    api("org.springframework.cloud:spring-cloud-function-context")
    api("org.springframework.cloud:spring-cloud-function-kotlin")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api("org.springframework.boot:spring-boot-autoconfigure")
}

apply(from = rootProject.file("gradle/publishing.gradle"))