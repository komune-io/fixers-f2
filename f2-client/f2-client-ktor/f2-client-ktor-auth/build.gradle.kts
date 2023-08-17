plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    kotlin("plugin.serialization")
//    id("dev.petuska.npm.publish")
}

dependencies {
    commonMainApi("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")

    commonMainImplementation("org.jetbrains.kotlinx:atomicfu:0.21.0")

    commonMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")

    commonMainImplementation("io.ktor:ktor-client-auth:${Versions.Kotlin.ktor}")
    commonMainImplementation("io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}")
    commonMainImplementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}")

    jvmTestImplementation("org.slf4j:slf4j-api:2.0.7")
    jvmTestImplementation("org.slf4j:slf4j-simple:2.0.7")
}
