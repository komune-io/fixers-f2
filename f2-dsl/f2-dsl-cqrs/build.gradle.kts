plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    Dependencies.Mpp.kotlinxDatetime(::commonMainApi)
    Dependencies.Jvm.Spring.dataCommons(::jvmMainImplementation)

}
