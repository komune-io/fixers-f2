plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")

}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    Dependencies.Jvm.Spring.cloudFunctionWebflux(::api)

    testImplementation(project(":f2-bdd:f2-bdd-spring-autoconfigure"))
}
