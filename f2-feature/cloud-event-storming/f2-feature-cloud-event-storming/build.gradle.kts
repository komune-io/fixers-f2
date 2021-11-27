import city.smartb.gradle.dependencies.FixersVersions

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    implementation("org.springframework.boot:spring-boot-starter-webflux:${FixersVersions.Spring.boot}")
    implementation("org.springframework.boot:spring-boot-autoconfigure:${FixersVersions.Spring.boot}")

    implementation("javax.persistence:javax.persistence-api:${Versions.javaxPersistence}")
    implementation("org.springframework:spring-context:${Versions.springFramework}")
    implementation("org.springframework.data:spring-data-commons:${FixersVersions.Spring.data}")

    implementation( "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")
}
