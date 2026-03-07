plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    kapt(libs.spring.boot.configuration.processor)

    api(project(":f2-dsl:f2-dsl-cqrs"))

    implementation(libs.bundles.spring.cloud.function)
    api(libs.spring.cloud.starter.function.webflux)
    api(libs.spring.boot.starter.webflux)
    api(libs.spring.boot.autoconfigure)
}
