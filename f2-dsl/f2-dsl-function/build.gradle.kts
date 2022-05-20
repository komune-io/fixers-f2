plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
//    id("dev.petuska.npm.publish")
}

dependencies {
    commonMainApi("org.jetbrains.kotlinx:kotlinx-coroutines-core:${city.smartb.gradle.dependencies.FixersVersions.Kotlin.coroutines}")
}
