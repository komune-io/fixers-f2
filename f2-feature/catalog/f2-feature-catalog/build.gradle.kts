plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {
    api("org.springframework.cloud:spring-cloud-function-context:${Versions.Spring.function}")
    api("org.springframework.cloud:spring-cloud-function-kotlin:${Versions.Spring.function}")
    api("org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}")
}
