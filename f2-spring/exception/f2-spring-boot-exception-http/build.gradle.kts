plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.fixers.gradle.publish)
}

dependencies {
    api(project(":f2-dsl:f2-dsl-cqrs"))
    implementation(libs.spring.web)
    implementation(libs.jackson.module.kotlin)

    testImplementation(libs.bundles.test.junit)
}
