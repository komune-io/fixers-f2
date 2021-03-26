plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-function"))

    Dependencies.jvm.coroutines.forEach { api(it) }

    api("org.springframework.cloud:spring-cloud-function-context:${Versions.springCloudFunction}")
    api("org.springframework.cloud:spring-cloud-function-kotlin:${Versions.springCloudFunction}")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")
    api("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")
}

apply(from = rootProject.file("gradle/publishing.gradle"))