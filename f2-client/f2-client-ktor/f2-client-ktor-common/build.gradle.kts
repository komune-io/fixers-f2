plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    //id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    Dependencies.Mpp.Ktor.clientCore(::commonMainApi)
    Dependencies.Mpp.Ktor.clientAuth(::commonMainApi)

    Dependencies.Mpp.Ktor.client(::commonMainApi, ::jvmMainApi, ::jsMainApi)

    Dependencies.Mpp.uuid(::commonMainApi)

    Dependencies.Jvm.Test.junit(::jvmTestImplementation)

    jvmTestImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}
