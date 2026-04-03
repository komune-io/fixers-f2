
pluginManagement {
	repositories {
		if(System.getenv("MAVEN_LOCAL_USE") == "true") {
			mavenLocal()
		}
		gradlePluginPortal()
		mavenCentral()
		maven { url = uri("https://central.sonatype.com/repository/maven-snapshots") }
	}
}

dependencyResolutionManagement {
	versionCatalogs {
		create("catalogue") {
			from(files("gradle/catalogue.versions.toml"))
		}
	}
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
	"f2-bdd:f2-bdd-spring-lambda",
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
