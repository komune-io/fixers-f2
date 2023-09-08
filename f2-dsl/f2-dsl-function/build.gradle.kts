plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("city.smartb.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    commonMainApi("org.jetbrains.kotlinx:kotlinx-coroutines-core:${city.smartb.gradle.dependencies.FixersVersions.Kotlin.coroutines}")
}
