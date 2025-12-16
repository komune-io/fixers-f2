plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.Spring.configurationProcessor(::kapt)

    api(project(":f2-dsl:f2-dsl-cqrs"))

    Dependencies.Jvm.Spring.cloudFunction(::implementation)
    Dependencies.Jvm.Spring.cloudFunctionWebflux(::api)
    Dependencies.Jvm.Spring.webflux(::api)
    Dependencies.Jvm.Spring.autoconfigure(::api)
}
