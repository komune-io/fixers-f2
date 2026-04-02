plugins {
	`java-platform`
	alias(catalogue.plugins.fixers.gradle.publish)
}

javaPlatform {
	allowDependencies()
}

dependencies {
	api(platform("org.springframework.boot:spring-boot-dependencies:${catalogue.versions.spring.boot.get()}"))
	api(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:${catalogue.versions.coroutines.get()}"))
	api(platform("org.jetbrains.kotlinx:kotlinx-serialization-bom:${catalogue.versions.serialization.get()}"))
	api(platform("io.ktor:ktor-bom:${catalogue.versions.ktor.get()}"))
	api(platform("io.opentelemetry:opentelemetry-bom:${catalogue.versions.opentelemetry.get()}"))
	api(platform("io.cucumber:cucumber-bom:${catalogue.versions.cucumber.get()}"))
	api(platform("io.arrow-kt:arrow-stack:${catalogue.versions.arrow.get()}"))
	api(platform("org.springframework.cloud:spring-cloud-dependencies:${catalogue.versions.spring.cloud.get()}"))
	api(platform("org.springdoc:springdoc-openapi-bom:${catalogue.versions.springdoc.get()}"))

	constraints {
		// ═══════════════════════════════════════════
		// F2 Modules
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
		api("io.komune.f2:f2-spring-boot-exception-http-webflux:${project.version}")
		api("io.komune.f2:f2-spring-boot-exception-http-mvc:${project.version}")
		api("io.komune.f2:f2-spring-boot-openapi:${project.version}")
		api("io.komune.f2:f2-spring-boot-openapi-mvc:${project.version}")
		api("io.komune.f2:f2-spring-boot-openapi-webflux:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-function:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-function-http:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-function-http-webflux:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-function-http-mvc:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-observability-opentelemetry:${project.version}")

		// ═══════════════════════════════════════════
		// Third-party deps (not managed by Spring Boot BOM)
		// ═══════════════════════════════════════════

		val datetimeVersion = catalogue.versions.datetime.get()
		val cloudeventsVersion = catalogue.versions.cloudevents.get()

		// Kotlin
		api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")

		// Cloud Events
		api("io.cloudevents:cloudevents-spring:$cloudeventsVersion")
	}
}