plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    commonMainApi(libs.kotlinx.serialization.json)

    commonMainApi(libs.ktor.client.core)
    commonMainApi(libs.ktor.client.auth)

    commonMainApi(libs.bundles.ktor.client.features)
    jvmMainApi(libs.ktor.client.java)
    jsMainApi(libs.ktor.client.js)

    commonTestImplementation(kotlin("test"))

}
