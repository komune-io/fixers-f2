plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    kapt("org.springframework.boot:spring-boot-configuration-processor:${Versions.Spring.boot}")

    api(project(":f2-dsl:f2-dsl-cqrs"))

    Dependencies.Jvm.Spring.cloudFunction(::implementation)
    Dependencies.Jvm.Spring.cloudFunctionWebflux(::api)
}
