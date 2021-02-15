plugins {
    kotlin("multiplatform")
    id("lt.petuska.npm.publish")
}

dependencies {
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))