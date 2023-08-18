plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    kotlin("plugin.serialization")
//    id("dev.petuska.npm.publish")
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
    commonMainApi("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-auth:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}")

    jsMainApi("io.ktor:ktor-client-json-js:${Versions.Kotlin.ktor}")

    jvmMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")
    jvmMainImplementation("io.ktor:ktor-client-jackson:${Versions.Kotlin.ktor}")

    jvmTestImplementation("org.slf4j:slf4j-api:2.0.7")
    jvmTestImplementation("org.slf4j:slf4j-simple:2.0.7")
}
