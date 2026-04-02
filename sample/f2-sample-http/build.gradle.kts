plugins {
    alias(catalogue.plugins.spring.boot)
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.kotlin.spring)
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {

    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-webflux"))
    implementation(project(":f2-spring:openapi:f2-spring-boot-openapi-webflux"))

    testImplementation(libs.bundles.spring.test)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
