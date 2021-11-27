import city.smartb.gradle.dependencies.FixersVersions

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {
    api("org.springframework.cloud:spring-cloud-function-context:${FixersVersions.Spring.function}")
    api("org.springframework.cloud:spring-cloud-function-kotlin:${FixersVersions.Spring.function}")
    api("org.springframework.boot:spring-boot-autoconfigure:${FixersVersions.Spring.boot}")
}
