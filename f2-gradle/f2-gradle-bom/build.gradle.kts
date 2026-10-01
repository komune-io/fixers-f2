plugins {
	`java-platform`
	alias(catalogue.plugins.fixers.gradle.publish)
}

javaPlatform {
	allowDependencies()
}

dependencies {
	api(platform(catalogue.spring.boot.dependencies)) { endorseStrictVersions() }
	api(platform(catalogue.kotlinx.coroutines.bom)) { endorseStrictVersions() }
	api(platform(catalogue.kotlinx.serialization.bom)) { endorseStrictVersions() }
	api(platform(catalogue.ktor.bom)) { endorseStrictVersions() }
	api(platform(catalogue.opentelemetry.bom)) { endorseStrictVersions() }
	api(platform(catalogue.cucumber.bom)) { endorseStrictVersions() }
	api(platform(catalogue.arrow.stack)) { endorseStrictVersions() }
	api(platform(catalogue.spring.cloud.dependencies)) { endorseStrictVersions() }
	api(platform(catalogue.springdoc.openapi.bom)) { endorseStrictVersions() }
	api(platform(catalogue.testcontainers.bom)) { endorseStrictVersions() }
	// Newer than what spring-boot-dependencies/spring-cloud-dependencies currently manage —
	// pulls in Dependabot-flagged Netty CVE fixes.
	api(platform(catalogue.netty.bom)) { endorseStrictVersions() }

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

		// f2-spring (auth)
		api("io.komune.f2:f2-spring-boot-starter-auth:${project.version}")
		api("io.komune.f2:f2-spring-boot-starter-auth-commons:${project.version}")
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

		// f2-bdd
		api("io.komune.f2:f2-bdd-config:${project.version}")
		api("io.komune.f2:f2-bdd-spring-autoconfigure:${project.version}")
		api("io.komune.f2:f2-bdd-spring-http:${project.version}")
		api("io.komune.f2:f2-bdd-spring-lambda:${project.version}")

		// f2-feature
		api("io.komune.f2:f2-feature-catalog:${project.version}")
		api("io.komune.f2:f2-feature-cloud-event-storming:${project.version}")
		api("io.komune.f2:f2-feature-version:${project.version}")

		// ═══════════════════════════════════════════
		// Third-party deps (not managed by Spring Boot BOM)
		// ═══════════════════════════════════════════

		// Kotlin
		api(catalogue.kotlinx.datetime)

		// KSP
		api(catalogue.ksp.symbol.processing.api)

		// Arrow KSP (not included in arrow-stack BOM)
		api(catalogue.arrow.optics.ksp.plugin)

		// Cloud Events
		api(catalogue.cloudevents.spring)

		// Jackson 3 (tools.jackson) — spring-cloud-dependencies currently manages a version
		// still inside the vulnerable range of a Dependabot-flagged CVE.
		api(catalogue.jackson3.databind)
	}
}