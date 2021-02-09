plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    api(project(":f2-spring:data:f2-spring-data"))
}
apply(from = rootProject.file("gradle/publishing.gradle"))