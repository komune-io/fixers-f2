plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    implementation("org.springframework.boot:spring-boot-starter-webflux:${Versions.Spring.boot}")
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}")

    implementation("javax.persistence:javax.persistence-api:${Versions.javaxPersistence}")
    implementation("org.springframework:spring-context:${Versions.Spring.framework}")
    implementation("org.springframework.data:spring-data-commons:${Versions.Spring.dataCommons}")

    implementation( "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")
}
