plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("dev.petuska.npm.publish")
}

dependencies {
//    jsApi("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${city.smartb.gradle.dependencies.FixersVersions.Kotlin.coroutines}")
    commonMainApi("org.jetbrains.kotlinx:kotlinx-coroutines-core:${city.smartb.gradle.dependencies.FixersVersions.Kotlin.coroutines}")
}
