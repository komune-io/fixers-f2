plugins {
    id("io.komune.fixers.gradle.kotlin.mpp")
    id("io.komune.fixers.gradle.publish")
    //id("io.komune.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    Dependencies.Jvm.Spring.dataCommons(::jvmMainImplementation)

    Dependencies.Jvm.Test.junit(::jvmTestImplementation)
}
