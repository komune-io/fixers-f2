plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("dev.petuska.npm.publish")
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
    commonMainApi("io.ktor:ktor-client-core:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}")
    commonMainApi("io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}")
    commonTestImplementation("org.jetbrains.kotlin:kotlin-test:${PluginVersions.kotlin}")

    jsApi("io.ktor:ktor-client-json-js:${Versions.Kotlin.ktor}")

    jvmMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")
    jvmMainImplementation("io.ktor:ktor-client-jackson:${Versions.Kotlin.ktor}")
    jvmMainApi("io.ktor:ktor-client-auth:${Versions.Kotlin.ktor}")
}
