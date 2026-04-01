plugins {
	`java-platform`
	alias(libs.plugins.fixers.publish)
}

javaPlatform {
	allowDependencies()
}

dependencies {
	api(platform("org.springframework.boot:spring-boot-dependencies:${libs.versions.spring.boot.get()}"))
	api(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:${libs.versions.coroutines.get()}"))
	api(platform("org.jetbrains.kotlinx:kotlinx-serialization-bom:${libs.versions.serialization.get()}"))
	api(platform("io.ktor:ktor-bom:${libs.versions.ktor.get()}"))
	api(platform("io.opentelemetry:opentelemetry-bom:${libs.versions.opentelemetry.get()}"))
	api(platform("io.cucumber:cucumber-bom:${libs.versions.cucumber.get()}"))
	api(platform("io.arrow-kt:arrow-stack:${libs.versions.arrow.get()}"))

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

		val datetimeVersion = libs.versions.datetime.get()
		val springFunctionVersion = libs.versions.spring.function.get()
		val cloudeventsVersion = libs.versions.cloudevents.get()
		val rsocketVersion = libs.versions.rsocket.get()
		val springdocVersion = libs.versions.springdoc.get()

		// Kotlin
		api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")

		// Spring Cloud Function
		api("org.springframework.cloud:spring-cloud-function-context:$springFunctionVersion")
		api("org.springframework.cloud:spring-cloud-function-kotlin:$springFunctionVersion")
		api("org.springframework.cloud:spring-cloud-starter-function-webflux:$springFunctionVersion")
		api("org.springframework.cloud:spring-cloud-starter-function-web:$springFunctionVersion")

		// Cloud Events
		api("io.cloudevents:cloudevents-spring:$cloudeventsVersion")

		api("io.rsocket.kotlin:rsocket-ktor-client:$rsocketVersion")

		// Springdoc
		api("org.springdoc:springdoc-openapi-starter-common:$springdocVersion")
		api("org.springdoc:springdoc-openapi-starter-webflux-ui:$springdocVersion")
		api("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
	}
}