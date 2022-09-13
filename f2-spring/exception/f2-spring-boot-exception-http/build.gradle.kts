plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    kapt("org.springframework.boot:spring-boot-configuration-processor:${Versions.Spring.boot}")

    Dependencies.Jvm.Spring.cloudFunction(::implementation)
    Dependencies.Jvm.Spring.cloudFunctionWebflux(::api)

    testImplementation(project(":f2-spring:function:f2-spring-boot-starter-function"))
    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
    testImplementation(project(":f2-client:f2-client-ktor"))
}
