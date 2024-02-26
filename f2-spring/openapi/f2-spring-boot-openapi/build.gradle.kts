plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.Spring.cloudFunction(::api)
    api("org.springdoc:springdoc-openapi-webflux-ui:${Versions.springdoc}")
}
