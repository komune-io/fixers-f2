plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    Dependencies.Jvm.Spring.webflux(::implementation)
    Dependencies.Jvm.Spring.autoconfigure(::implementation)

    Dependencies.Jvm.Spring.dataCommons(::implementation)
    Dependencies.Jvm.Json.jackson(::implementation)
}
