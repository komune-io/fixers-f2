plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.ktor.client.logging)
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-http"))
}
