plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${city.smartb.gradle.dependencies.FixersVersions.Spring.boot}")

    api("org.springframework.boot:spring-boot-starter-test:${city.smartb.gradle.dependencies.FixersVersions.Spring.boot}")

    api("de.flapdoodle.embed:de.flapdoodle.embed.mongo:${Versions.embedMongo}")
}
