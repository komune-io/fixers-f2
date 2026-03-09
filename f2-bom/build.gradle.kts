plugins {
	`java-platform`
	alias(libs.plugins.fixers.publish)
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
		// Third-party deps
		// ═══════════════════════════════════════════

		val coroutinesVersion = libs.versions.coroutines.get()
		val serializationVersion = libs.versions.serialization.get()
		val datetimeVersion = libs.versions.datetime.get()
		val ktorVersion = libs.versions.ktor.get()
		val springBootVersion = libs.versions.spring.boot.get()
		val springFrameworkVersion = libs.versions.spring.framework.get()
		val springDataVersion = libs.versions.spring.data.get()
		val springSecurityVersion = libs.versions.spring.security.get()
		val springReactorVersion = libs.versions.spring.reactor.get()
		val jakartaPersistenceVersion = libs.versions.jakarta.persistence.get()
		val jacksonVersion = libs.versions.jackson.get()
		val slf4jVersion = libs.versions.slf4j.get()
		val junitVersion = libs.versions.junit.asProvider().get()
		val junitPlatformVersion = libs.versions.junit.platform.get()
		val assertjVersion = libs.versions.assertj.get()
		val cucumberVersion = libs.versions.cucumber.get()
		val springFunctionVersion = libs.versions.spring.function.get()
		val cloudeventsVersion = libs.versions.cloudevents.get()
		val micrometerTracingVersion = libs.versions.micrometer.tracing.get()
		val micrometerVersion = libs.versions.micrometer.asProvider().get()
		val opentelemetryVersion = libs.versions.opentelemetry.get()
		val rsocketVersion = libs.versions.rsocket.get()
		val springdocVersion = libs.versions.springdoc.get()
		val gsonVersion = libs.versions.gson.get()
		val arrowVersion = libs.versions.arrow.get()

		// Kotlin
		api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
		api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")
		api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutinesVersion")
		api("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
		api("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
		api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
		api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")
		api("io.ktor:ktor-client-core:$ktorVersion")
		api("io.ktor:ktor-client-auth:$ktorVersion")
		api("io.ktor:ktor-utils:$ktorVersion")
		api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
		api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
		api("io.ktor:ktor-client-logging:$ktorVersion")
		api("io.ktor:ktor-client-java:$ktorVersion")
		api("io.ktor:ktor-client-js:$ktorVersion")
		api("io.ktor:ktor-client-cio:$ktorVersion")
		api("io.ktor:ktor-client-mock:$ktorVersion")
		api("io.ktor:ktor-serialization-jackson:$ktorVersion")

		// Spring
		api("org.springframework.boot:spring-boot-autoconfigure:$springBootVersion")
		api("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-opentelemetry:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
		api("org.springframework.boot:spring-boot-restclient:$springBootVersion")
		api("org.springframework.boot:spring-boot-resttestclient:$springBootVersion")
		api("org.springframework.boot:spring-boot-testcontainers:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-data-redis-reactive:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:$springBootVersion")
		api("org.springframework.boot:spring-boot-starter-data-r2dbc:$springBootVersion")
		api("org.springframework:spring-context:$springFrameworkVersion")
		api("org.springframework:spring-web:$springFrameworkVersion")
		api("org.springframework:spring-tx:$springFrameworkVersion")
		api("org.springframework.data:spring-data-commons:$springDataVersion")
		api("org.springframework.security:spring-security-oauth2-resource-server:$springSecurityVersion")
		api("org.springframework.security:spring-security-oauth2-jose:$springSecurityVersion")
		api("io.projectreactor:reactor-test:$springReactorVersion")
		api("jakarta.persistence:jakarta.persistence-api:$jakartaPersistenceVersion")

		// JSON
		api("tools.jackson.module:jackson-module-kotlin:$jacksonVersion")

		// Logging
		api("org.slf4j:slf4j-api:$slf4jVersion")

		// Test
		api("org.junit.jupiter:junit-jupiter:$junitVersion")
		api("org.junit.jupiter:junit-jupiter-api:$junitVersion")
		api("org.junit.platform:junit-platform-suite:$junitPlatformVersion")
		api("org.assertj:assertj-core:$assertjVersion")
		api("io.cucumber:cucumber-java:$cucumberVersion")
		api("io.cucumber:cucumber-java8:$cucumberVersion")
		api("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")

		// ═══════════════════════════════════════════
		// F2-specific deps
		// ═══════════════════════════════════════════

		// Spring Cloud Function
		api("org.springframework.cloud:spring-cloud-function-context:$springFunctionVersion")
		api("org.springframework.cloud:spring-cloud-function-kotlin:$springFunctionVersion")
		api("org.springframework.cloud:spring-cloud-starter-function-webflux:$springFunctionVersion")

		// Cloud Events
		api("io.cloudevents:cloudevents-spring:$cloudeventsVersion")

		// Observability
		api("io.micrometer:micrometer-tracing-bridge-otel:$micrometerTracingVersion")
		api("io.micrometer:micrometer-registry-otlp:$micrometerVersion")
		api("io.opentelemetry:opentelemetry-exporter-otlp:$opentelemetryVersion")
		api("io.opentelemetry:opentelemetry-sdk-testing:$opentelemetryVersion")
		api("io.opentelemetry:opentelemetry-sdk-extension-autoconfigure:$opentelemetryVersion")

		// RSocket
		api("io.rsocket.kotlin:rsocket-ktor-client:$rsocketVersion")

		// Springdoc
		api("org.springdoc:springdoc-openapi-webflux-ui:$springdocVersion")

		// Gson
		api("com.google.code.gson:gson:$gsonVersion")

		// Arrow
		api("io.arrow-kt:arrow-core:$arrowVersion")
		api("io.arrow-kt:arrow-optics:$arrowVersion")
		api("io.arrow-kt:arrow-optics-ksp-plugin:$arrowVersion")
	}
}
