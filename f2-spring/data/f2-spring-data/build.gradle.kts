import city.smartb.gradle.dependencies.FixersVersions

plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-autoconfigure:${FixersVersions.Spring.boot}")

    implementation("javax.persistence:javax.persistence-api:${Versions.javaxPersistence}")
    implementation("org.springframework:spring-context:${Versions.springFramework}")
    implementation("org.springframework.data:spring-data-commons:${FixersVersions.Spring.data}")

    implementation( "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonKotlin}")
}
