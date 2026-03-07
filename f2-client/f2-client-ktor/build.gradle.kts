plugins {
    alias(libs.plugins.fixers.kotlin.mpp)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.ktor.client.logging)
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-http"))
}
