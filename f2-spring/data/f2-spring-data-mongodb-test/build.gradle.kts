import city.smartb.gradle.dependencies.FixersVersions

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${FixersVersions.Spring.boot}")

    api("org.springframework.boot:spring-boot-starter-test:${FixersVersions.Spring.boot}") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    api("de.flapdoodle.embed:de.flapdoodle.embed.mongo:${Versions.embedMongo}")
}
