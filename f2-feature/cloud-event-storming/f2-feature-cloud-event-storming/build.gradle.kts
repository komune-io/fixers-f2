plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.autoconfigure)

    implementation(libs.bundles.spring.data.commons)
    implementation(libs.jackson.module.kotlin)
}
