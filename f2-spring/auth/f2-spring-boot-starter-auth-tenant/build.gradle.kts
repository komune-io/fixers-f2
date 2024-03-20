import io.komune.gradle.dependencies.Scope
import io.komune.gradle.dependencies.add

plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.Spring.configurationProcessor(::kapt)
    Dependencies.Jvm.Spring.security(::api)
    Dependencies.Jvm.Spring.oauth2(::api)
//    Dependencies.Jvm.Spring.autoconfigure(::implementation)
}


