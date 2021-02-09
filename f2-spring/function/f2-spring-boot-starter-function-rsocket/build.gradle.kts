plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
}

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven("https://repo.spring.io/snapshot")
    maven ("https://repo.spring.io/milestone")
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api("org.springframework.cloud:spring-cloud-function-rsocket")
}

apply(from = rootProject.file("gradle/publishing.gradle"))