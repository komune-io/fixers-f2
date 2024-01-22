plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("city.smartb.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
    commonMainApi("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-auth:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}")

//    jsMainApi("io.ktor:ktor-client-json-js:${Versions.Kotlin.ktor}")

    jvmMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")
    jvmMainImplementation("io.ktor:ktor-client-jackson:${Versions.Kotlin.ktor}")

    Dependencies.Jvm.Test.junit(::jvmTestImplementation)
    jvmTestImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}
