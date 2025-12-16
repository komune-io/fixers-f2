plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.Spring.cloudFunction(::api)
    Dependencies.Jvm.Spring.springdocOpenapi(::api)
}
