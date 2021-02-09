plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))

    implementation("org.springframework.boot:spring-boot-autoconfigure")

    implementation("javax.persistence:javax.persistence-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")

    implementation( "com.fasterxml.jackson.module:jackson-module-kotlin")
}

apply(from = rootProject.file("gradle/publishing.gradle"))