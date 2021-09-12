plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {
    api("org.springframework.cloud:spring-cloud-function-context:${Versions.springCloudFunction}")
    api("org.springframework.cloud:spring-cloud-function-kotlin:${Versions.springCloudFunction}")
    api("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")
}
