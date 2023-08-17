plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
//    id("dev.petuska.npm.publish")
}

dependencies {
    commonMainApi("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")

    commonMainImplementation("org.jetbrains.kotlinx:atomicfu:0.21.0")

    commonMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")

    commonMainImplementation("io.ktor:ktor-client-auth:${Versions.Kotlin.ktor}")
    commonMainImplementation("io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}")
    commonMainImplementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}")

}
