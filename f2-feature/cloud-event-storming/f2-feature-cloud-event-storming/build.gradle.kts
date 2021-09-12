plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    implementation("org.springframework.boot:spring-boot-starter-webflux:${Versions.springBoot}")
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")

    implementation("javax.persistence:javax.persistence-api:${Versions.javaxPersistence}")
    implementation("org.springframework:spring-context:${Versions.springFramework}")
    implementation("org.springframework.data:spring-data-commons:${Versions.springData}")

    implementation( "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")
}
