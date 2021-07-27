plugins {
    kotlin("multiplatform")
    id("lt.petuska.npm.publish")
}

dependencies {
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))
