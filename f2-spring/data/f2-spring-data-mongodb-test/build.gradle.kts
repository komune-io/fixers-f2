plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
//    kotlin("plugin.jpa")
//    kotlin("plugin.spring")
}

dependencies {
    api ("org.junit.jupiter:junit-jupiter-api")

    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    api("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    api("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}

apply(from = rootProject.file("gradle/publishing.gradle"))