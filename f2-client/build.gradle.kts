plugins {
    kotlin("multiplatform")
}

kotlin {
    dependencies {
        commonMainApi(project(":f2-dsl:f2-dsl-function"))
    }
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))
