plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api(project(":f2-spring:exception:f2-spring-boot-exception-http"))
    Dependencies.Jvm.Spring.cloudFunctionWebflux(::api)

    testImplementation("io.cucumber:cucumber-java:7.9.0")

    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
    testImplementation(project(":f2-client:f2-client-ktor"))
}
