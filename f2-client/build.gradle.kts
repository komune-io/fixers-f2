plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")

}

kotlin {
    dependencies {
        commonMainApi(project(":f2-dsl:f2-dsl-function"))
    }
}
