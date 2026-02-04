plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    Dependencies.Jvm.Spring.cloudFunctionDep(::compileOnly)
    Dependencies.Jvm.Spring.slf4J(::implementation)

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-function"))

    Dependencies.Jvm.Kotlin.coroutines(::api)
    Dependencies.Jvm.Spring.cloudFunction(::api)

    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
}
