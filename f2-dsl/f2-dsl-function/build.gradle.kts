
plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    // id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    Dependencies.Jvm.Test.junit(::jvmTestImplementation)

}

tasks.withType<Test> {
    useJUnitPlatform()
}
