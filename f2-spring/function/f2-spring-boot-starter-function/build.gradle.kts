plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    Dependencies.Jvm.Spring.cloudFunctionDep(::compileOnly)

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-function"))

    Dependencies.Jvm.Kotlin.coroutines(::api)
    Dependencies.Jvm.Spring.cloudFunction(::api)

    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
}
