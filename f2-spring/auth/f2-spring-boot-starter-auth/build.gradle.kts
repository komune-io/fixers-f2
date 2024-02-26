plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("plugin.spring")
}

dependencies {

    Dependencies.Jvm.Spring.security(::api)
    Dependencies.Jvm.Spring.oauth2(::api)

    Dependencies.Jvm.Spring.autoconfigure(::implementation)
}
