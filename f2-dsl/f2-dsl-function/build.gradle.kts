plugins {
    kotlin("multiplatform")
    id("lt.petuska.npm.publish")
}

dependencies {
    jvmApi("io.projectreactor:reactor-core:${Versions.reactor}")
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))
