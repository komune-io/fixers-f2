plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))

    Dependencies.Jvm.Spring.autoconfigure(::api)
}
