plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(libs.springdoc.openapi.common)
    api(libs.spring.web)
    api(project(":f2-dsl:f2-dsl-function"))

    testImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-mvc"))
    testImplementation(libs.springdoc.openapi.ui)
    testImplementation(libs.bundles.spring.test)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
