import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.FixersDependencies

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-function"))

    FixersDependencies.Jvm.Kotlin.coroutines(::api)

    api("org.springframework.cloud:spring-cloud-function-context:${FixersVersions.Spring.function}")
    api("org.springframework.cloud:spring-cloud-function-kotlin:${FixersVersions.Spring.function}")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")
    api("org.springframework.boot:spring-boot-autoconfigure:${FixersVersions.Spring.boot}")
}
