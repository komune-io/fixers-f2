
plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    kapt(libs.spring.boot.configuration.processor)
    api(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons"))
    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)

    implementation(libs.reactor.core)

    testImplementation(testFixtures(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons")))
    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.spring.boot.starter.webflux)
}


