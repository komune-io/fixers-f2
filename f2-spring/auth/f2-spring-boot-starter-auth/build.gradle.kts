plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {
    api(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons"))

    api(libs.spring.boot.starter.security)
    api(libs.bundles.spring.oauth2)

    implementation(libs.reactor.core)
    implementation(libs.spring.boot.autoconfigure)

    testImplementation(testFixtures(project(":f2-spring:auth:f2-spring-boot-starter-auth-commons")))
    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.spring.boot.starter.webflux)
}
