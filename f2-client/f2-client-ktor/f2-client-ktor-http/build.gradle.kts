plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    //id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    commonMainApi("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-auth:${Versions.Kotlin.ktor}")

    commonMainApi("io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-logging:${Versions.Kotlin.ktor}")

    jvmMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")

    Dependencies.Mpp.uuid(::jsMainApi)


    Dependencies.Jvm.Test.junit(::jvmTestImplementation)
    jvmTestImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}
