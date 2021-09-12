plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${Versions.springBoot}")

    api("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    api("de.flapdoodle.embed:de.flapdoodle.embed.mongo:${Versions.embedMongo}")
}
