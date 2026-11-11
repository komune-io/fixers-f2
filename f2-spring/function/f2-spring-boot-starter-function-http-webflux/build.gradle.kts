plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function-http"))
    api(project(":f2-spring:exception:f2-spring-boot-exception-http-webflux"))
    api(libs.spring.cloud.starter.function.webflux)

    testImplementation(project(":f2-bdd:f2-bdd-spring-http"))
}
