plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    implementation("org.springframework.boot:spring-boot-starter-webflux:${Versions.Spring.boot}")
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}")

    Dependencies.Jvm.Spring.dataCommons(::implementation)
    Dependencies.Jvm.Json.jackson(::implementation)
}
