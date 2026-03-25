plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
}

dependencies {
    api(project(":f2-dsl:f2-dsl-cqrs"))
    implementation(libs.spring.web)
    implementation(libs.jackson.module.kotlin)
}
