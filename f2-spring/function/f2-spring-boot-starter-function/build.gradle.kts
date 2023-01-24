plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {

    compileOnly("com.google.code.gson:gson:2.10.1")
    compileOnly("io.cloudevents:cloudevents-spring:2.4.1")
    compileOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-function"))

    Dependencies.Jvm.Kotlin.coroutines(::api)
    Dependencies.Jvm.Spring.cloudFunction(::api)

    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
}
