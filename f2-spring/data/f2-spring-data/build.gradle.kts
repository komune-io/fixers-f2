plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}")

    Dependencies.Jvm.Spring.dataCommons(::implementation)
    Dependencies.Jvm.Json.jackson(::implementation)
}
