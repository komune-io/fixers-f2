import io.komune.fixers.gradle.dependencies.FixersVersions

plugins {
	`java-platform`
	id("io.komune.fixers.gradle.publish")
}

dependencies {
	constraints {
		// ═══════════════════════════════════════════
		// F2 Modules (16 published modules)
		// ═══════════════════════════════════════════

		// f2-dsl
		api("io.komune.f2:f2-dsl-function:${project.version}")
		api("io.komune.f2:f2-dsl-cqrs:${project.version}")
		api("io.komune.f2:f2-dsl-event:${project.version}")

		// f2-client
		api("io.komune.f2:f2-client-core:${project.version}")
		api("io.komune.f2:f2-client-domain:${project.version}")
		api("io.komune.f2:f2-client-ktor:${project.version}")
		api("io.komune.f2:f2-client-ktor-common:${project.version}")
		api("io.komune.f2:f2-client-ktor-http:${project.version}")

		// f2-spring
		api("io.komune.f2:f2-spring-boot-starter-auth:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-auth-keycloak:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-auth-tenant:${project.version}")
		api("io.komune.f2:f2-spring-boot-exception-http:${project.version}")
		api("io.komune.f2:f2-spring-boot-openapi:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-function:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-function-http:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-observability-opentelemetry:${project.version}")

		// ═══════════════════════════════════════════
		// Third-party deps from FixersVersions/FixersDependencies
		// (fixers-gradle Dependencies.kt)
		// ═══════════════════════════════════════════

		// Kotlin
		api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${FixersVersions.Kotlin.coroutines}")
		api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${FixersVersions.Kotlin.coroutines}")
		api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${FixersVersions.Kotlin.coroutines}")
		api("org.jetbrains.kotlinx:kotlinx-coroutines-test:${FixersVersions.Kotlin.coroutines}")
		api("org.jetbrains.kotlinx:kotlinx-serialization-core:${FixersVersions.Kotlin.serialization}")
		api("org.jetbrains.kotlinx:kotlinx-serialization-json:${FixersVersions.Kotlin.serialization}")
		api("org.jetbrains.kotlinx:kotlinx-datetime:${FixersVersions.Kotlin.datetime}")
		api("io.ktor:ktor-client-core:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-auth:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-utils:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-content-negotiation:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-serialization-kotlinx-json:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-logging:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-java:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-js:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-cio:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-client-mock:${FixersVersions.Kotlin.ktor}")
		api("io.ktor:ktor-serialization-jackson:${FixersVersions.Kotlin.ktor}")

		// Spring
		api("org.springframework.boot:spring-boot-autoconfigure:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-configuration-processor:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-security:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-test:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-actuator:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-opentelemetry:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-webflux:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-restclient:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-resttestclient:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-testcontainers:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-data-redis-reactive:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${FixersVersions.Spring.boot}")
		api("org.springframework.boot:spring-boot-starter-data-r2dbc:${FixersVersions.Spring.boot}")
		api("org.springframework:spring-context:${FixersVersions.Spring.framework}")
		api("org.springframework:spring-web:${FixersVersions.Spring.framework}")
		api("org.springframework:spring-tx:${FixersVersions.Spring.framework}")
		api("org.springframework.data:spring-data-commons:${FixersVersions.Spring.data}")
		api("org.springframework.security:spring-security-oauth2-resource-server:${FixersVersions.Spring.security}")
		api("org.springframework.security:spring-security-oauth2-jose:${FixersVersions.Spring.security}")
		api("io.projectreactor:reactor-test:${FixersVersions.Spring.reactor}")
		api("jakarta.persistence:jakarta.persistence-api:${FixersVersions.Spring.jakartaPersistence}")

		// JSON
		api("tools.jackson.module:jackson-module-kotlin:${FixersVersions.Json.jackson}")

		// Logging
		api("org.slf4j:slf4j-api:${FixersVersions.Logging.slf4j}")

		// Test
		api("org.junit.jupiter:junit-jupiter:${FixersVersions.Test.junit}")
		api("org.junit.jupiter:junit-jupiter-api:${FixersVersions.Test.junit}")
		api("org.junit.platform:junit-platform-suite:${FixersVersions.Test.junitPlatform}")
		api("org.assertj:assertj-core:${FixersVersions.Test.assertj}")
		api("io.cucumber:cucumber-java:${FixersVersions.Test.cucumber}")
		api("io.cucumber:cucumber-java8:${FixersVersions.Test.cucumber}")
		api("io.cucumber:cucumber-junit-platform-engine:${FixersVersions.Test.cucumber}")

		// ═══════════════════════════════════════════
		// F2-specific deps (from buildSrc/Dependencies.kt)
		// ═══════════════════════════════════════════

		// Spring Cloud Function
		api("org.springframework.cloud:spring-cloud-function-context:${Versions.Spring.function}")
		api("org.springframework.cloud:spring-cloud-function-kotlin:${Versions.Spring.function}")
		api("org.springframework.cloud:spring-cloud-starter-function-webflux:${Versions.Spring.function}")

		// Cloud Events
		api("io.cloudevents:cloudevents-spring:${Versions.CloudEvent.spring}")

		// Observability
		api("io.micrometer:micrometer-tracing-bridge-otel:${Versions.Observability.micrometerTracing}")
		api("io.micrometer:micrometer-registry-otlp:${Versions.Observability.micrometer}")
		api("io.opentelemetry:opentelemetry-exporter-otlp:${Versions.Observability.opentelemetry}")
		api("io.opentelemetry:opentelemetry-sdk-testing:${Versions.Observability.opentelemetry}")

		// RSocket
		api("io.rsocket.kotlin:rsocket-ktor-client:${Versions.rsocket}")

		// Springdoc
		api("org.springdoc:springdoc-openapi-webflux-ui:${Versions.springdoc}")

		// Gson
		api("com.google.code.gson:gson:${Versions.Json.gson}")

		// Arrow
		val arrowVersion = "2.2.1.1"
		api("io.arrow-kt:arrow-core:$arrowVersion")
		api("io.arrow-kt:arrow-optics:$arrowVersion")
		api("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
	}
}
