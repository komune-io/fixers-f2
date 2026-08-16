plugins {
    alias(catalogue.plugins.spring.boot)
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-mvc"))
    implementation(project(":f2-spring:openapi:f2-spring-boot-openapi-mvc"))

    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.bundles.spring.test)
}
