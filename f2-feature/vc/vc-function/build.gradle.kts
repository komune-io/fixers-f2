plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    api(project(":f2-feature:vc:vc-model"))
    api(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kserialization}")

    api("city.smartb.iris:iris-vc:${Versions.vc}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}")
    testImplementation(project(":f2-feature:vc:vc-client"))
}
