
pluginManagement {
    // One fixers version for the build: the catalogue's, which the settings plugin also checks.
    val fixersVersion = settingsDir.resolve("gradle/libs.versions.toml").readLines()
        .firstNotNullOf { Regex("^fixers\\s*=\\s*\"([^\"]+)\"").find(it)?.groupValues?.get(1) }
    plugins {
        id("io.komune.fixers.gradle.settings") version fixersVersion
    }
    repositories {
        // A release builds each fixers repository against the previous one's artifacts in Maven Local.
        if (providers.environmentVariable("FIXERS_REPOSITORIES_MAVEN_LOCAL").orNull == "true") {
            mavenLocal { content { includeGroupByRegex("io\\.komune(\\..*)?") } }
        }
        gradlePluginPortal()
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots") {
            mavenContent { snapshotsOnly() }
            content { includeGroupByRegex("io\\.komune(\\..*)?") }
        }
    }
}

// Before the settings plugin's repositories: a release resolves the io.komune artifacts it just built from Maven Local.
dependencyResolutionManagement {
    repositories {
        if (providers.environmentVariable("FIXERS_REPOSITORIES_MAVEN_LOCAL").orNull == "true") {
            mavenLocal { content { includeGroupByRegex("io\\.komune(\\..*)?") } }
        }
    }
}

// The repositories of the build: Maven Central and the io.komune snapshots, nothing a build script may add.
// It loads no `catalogue` (gradle.properties): this build produces it and reads its own, gradle/libs.versions.toml, as `libs`.
plugins {
    id("io.komune.fixers.gradle.settings")
}

rootProject.name = "fixers-f2"

include(
    "f2-gradle:f2-gradle-bom",
    "f2-gradle:f2-gradle-catalog",
    "f2-gradle:f2-gradle-plugin",
)

include(
    "f2-bdd:f2-bdd-config",
    "f2-bdd:f2-bdd-spring-autoconfigure",
    "f2-bdd:f2-bdd-spring-http",
    "f2-bdd:f2-bdd-spring-lambda",
    "f2-bdd:f2-bdd-blockhound",
)

include(
    "f2-client:f2-client-core",
    "f2-client:f2-client-domain",
    "f2-client:f2-client-ktor",
    "f2-client:f2-client-ktor:f2-client-ktor-common",
    "f2-client:f2-client-ktor:f2-client-ktor-http",
)

include(
    "f2-dsl:f2-dsl-function",
    "f2-dsl:f2-dsl-cqrs",
    "f2-dsl:f2-dsl-event"
)

include(
    "f2-spring:auth:f2-spring-boot-starter-auth-commons",
    "f2-spring:auth:f2-spring-boot-starter-auth",
    "f2-spring:auth:f2-spring-boot-starter-auth-keycloak",
    "f2-spring:auth:f2-spring-boot-starter-auth-tenant",
)

include(
    "f2-spring:exception:f2-spring-boot-exception-http",
    "f2-spring:exception:f2-spring-boot-exception-http-webflux",
    "f2-spring:exception:f2-spring-boot-exception-http-mvc",
)

include(
    "f2-spring:openapi:f2-spring-boot-openapi",
    "f2-spring:openapi:f2-spring-boot-openapi-mvc",
    "f2-spring:openapi:f2-spring-boot-openapi-webflux",
)

include(
    "f2-spring:function:f2-spring-boot-starter-function",
    "f2-spring:function:f2-spring-boot-starter-function-http",
    "f2-spring:function:f2-spring-boot-starter-function-http-webflux",
    "f2-spring:function:f2-spring-boot-starter-function-http-mvc",
    "f2-spring:function:f2-spring-boot-starter-observability-opentelemetry"
)

include(
    "f2-feature:catalog:f2-feature-catalog",
    "f2-feature:cloud-event-storming:f2-feature-cloud-event-storming",
    "f2-feature:version:f2-feature-version"
)

include(
    "sample:f2-sample-http",
    "sample:f2-sample-http-mvc",
)
