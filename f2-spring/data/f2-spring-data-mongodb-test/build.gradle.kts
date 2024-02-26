plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${Versions.Spring.boot}")

    api("org.springframework.boot:spring-boot-starter-test:${Versions.Spring.boot}")

    api("de.flapdoodle.embed:de.flapdoodle.embed.mongo:${Versions.embedMongo}")
}
