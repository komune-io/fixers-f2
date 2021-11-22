import city.smartb.gradle.dependencies.FixersVersions

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${FixersVersions.Spring.boot}")
    api(project(":f2-spring:data:f2-spring-data"))
}
