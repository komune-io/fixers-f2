plugins {
    alias(libs.plugins.fixers.kotlin.mpp)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-client:f2-client-domain"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-common"))

    commonMainApi(libs.ktor.client.core)
    commonMainApi(libs.ktor.client.auth)

    commonMainApi(libs.bundles.ktor.client.features)
    jvmMainApi(libs.ktor.client.java)
    jsMainApi(libs.ktor.client.js)

    jvmTestImplementation(libs.bundles.test.junit)
    jvmTestImplementation(libs.ktor.client.mock)

    jvmTestImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}
