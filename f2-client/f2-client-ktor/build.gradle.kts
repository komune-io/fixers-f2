plugins {
    alias(libs.plugins.fixers.gradle.kotlin.mpp)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.ktor.client.logging)
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-http"))

    jvmTestImplementation(libs.bundles.test.junit)
}
