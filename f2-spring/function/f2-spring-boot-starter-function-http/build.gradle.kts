import city.smartb.gradle.dependencies.FixersVersions

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")

}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api("org.springframework.cloud:spring-cloud-starter-function-webflux:${FixersVersions.Spring.function}")
}
