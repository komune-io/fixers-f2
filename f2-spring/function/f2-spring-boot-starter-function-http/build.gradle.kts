plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api(project(":f2-spring:exception:f2-spring-boot-exception-http"))
    api(libs.spring.cloud.starter.function.webflux)

    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
    testImplementation(project(":f2-client:f2-client-ktor"))
}
