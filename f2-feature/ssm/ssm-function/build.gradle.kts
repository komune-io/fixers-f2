plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jetbrains.kotlin.kapt")
}

dependencies {
    api(project(":f2-feature:ssm:ssm-model"))
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))

    api ("city.smartb.ssm:ssm-sdk-client:${Versions.ssm}")
    api ("city.smartb.ssm:ssm-sdk-client-spring:${Versions.ssm}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

}

apply(from = rootProject.file("gradle/publishing.gradle"))