plugins {
    `java-platform`
    alias(libs.plugins.fixers.gradle.publish)
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform(libs.spring.boot.dependencies)) { endorseStrictVersions() }
    api(platform(libs.kotlinx.coroutines.bom)) { endorseStrictVersions() }
    api(platform(libs.kotlinx.serialization.bom)) { endorseStrictVersions() }
    api(platform(libs.ktor.bom)) { endorseStrictVersions() }
    api(platform(libs.opentelemetry.bom)) { endorseStrictVersions() }
    api(platform(libs.cucumber.bom)) { endorseStrictVersions() }
    api(platform(libs.arrow.stack)) { endorseStrictVersions() }
    api(platform(libs.spring.cloud.dependencies)) { endorseStrictVersions() }
    api(platform(libs.springdoc.openapi.bom)) { endorseStrictVersions() }
    api(platform(libs.testcontainers.bom)) { endorseStrictVersions() }
    api(platform(libs.cloudevents.bom)) { endorseStrictVersions() }
    api(platform(libs.netty.bom)) { endorseStrictVersions() }
    api(platform(libs.jackson3.bom)) { endorseStrictVersions() }
    api(platform(libs.jackson2.bom)) { endorseStrictVersions() }

    constraints {
        // ═══════════════════════════════════════════
        // F2 Modules
        // ═══════════════════════════════════════════

        // f2-dsl
        api("io.komune.f2:f2-dsl-function:${project.version}")
        api("io.komune.f2:f2-dsl-cqrs:${project.version}")

        // f2-client
        api("io.komune.f2:f2-client-core:${project.version}")
        api("io.komune.f2:f2-client-domain:${project.version}")
        api("io.komune.f2:f2-client-ktor:${project.version}")
        api("io.komune.f2:f2-client-ktor-common:${project.version}")
        api("io.komune.f2:f2-client-ktor-http:${project.version}")

        // f2-spring (auth)
        api("io.komune.f2:f2-spring-boot-starter-auth:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-auth-commons:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-auth-keycloak:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-auth-tenant:${project.version}")
        api("io.komune.f2:f2-spring-boot-exception-http:${project.version}")
        api("io.komune.f2:f2-spring-boot-exception-http-webflux:${project.version}")
        api("io.komune.f2:f2-spring-boot-exception-http-mvc:${project.version}")
        api("io.komune.f2:f2-spring-boot-openapi:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-function:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-function-http:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-function-http-webflux:${project.version}")
        api("io.komune.f2:f2-spring-boot-starter-function-http-mvc:${project.version}")

        // Never published, so no constraint: f2-bdd-*, f2-feature-*, f2-dsl-event, f2-spring-boot-openapi-mvc,
        // f2-spring-boot-openapi-webflux, f2-spring-boot-starter-observability-opentelemetry (README, "Modules not published").

        // ═══════════════════════════════════════════
        // Third-party deps
        // ═══════════════════════════════════════════

        // Kotlin
        api(libs.kotlinx.datetime)

        // PostgreSQL
        api(libs.scram.client)

        // KSP
        api(libs.ksp.symbol.processing.api)
        api(libs.arrow.optics.ksp.plugin)
    }
}