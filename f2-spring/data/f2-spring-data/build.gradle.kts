plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-autoconfigure")

    implementation("javax.persistence:javax.persistence-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")

    implementation( "com.fasterxml.jackson.module:jackson-module-kotlin")
}

apply(from = rootProject.file("gradle/publishing.gradle"))