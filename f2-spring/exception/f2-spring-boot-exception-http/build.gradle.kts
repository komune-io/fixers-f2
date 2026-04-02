plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
}

dependencies {
    api(project(":f2-dsl:f2-dsl-cqrs"))
    implementation(libs.spring.web)
    implementation(libs.jackson.module.kotlin)
}
