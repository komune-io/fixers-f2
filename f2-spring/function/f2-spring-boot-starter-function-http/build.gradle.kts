plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    api(project(":f2-spring:exception:f2-spring-boot-exception-http"))
    implementation(libs.spring.cloud.function.web)

    testImplementation(libs.bundles.test.junit)
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}
