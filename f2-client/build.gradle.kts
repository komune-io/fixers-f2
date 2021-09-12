plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
}

kotlin {
    dependencies {
        commonMainApi(project(":f2-dsl:f2-dsl-function"))
    }
}
