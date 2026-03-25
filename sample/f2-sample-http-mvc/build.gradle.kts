plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {
    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-mvc"))
    testImplementation(libs.bundles.spring.test)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
