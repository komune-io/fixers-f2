
pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		maven { url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots") }
	}
}

rootProject.name = "fixers-f2"

include(
	"f2-bdd:f2-bdd-config",
	"f2-bdd:f2-bdd-spring-autoconfigure",
	"f2-bdd:f2-bdd-spring-lambda",
)

include(
	"f2-client:f2-client-core",
	"f2-client:f2-client-ktor",
	"f2-client:f2-client-ktor:f2-client-ktor-http",
	"f2-client:f2-client-ktor:f2-client-ktor-rsocket"
)

include(
	"f2-dsl:f2-dsl-function",
	"f2-dsl:f2-dsl-cqrs",
	"f2-dsl:f2-dsl-event"
)

include(
	"f2-spring:auth:f2-spring-boot-starter-auth",
	"f2-spring:auth:f2-spring-boot-starter-auth-keycloak",
	"f2-spring:auth:f2-spring-boot-starter-auth-tenant",
)

// TODO: Disable data need before removing it
//include(
//	"f2-spring:data:f2-spring-data",
//	"f2-spring:data:f2-spring-data-mongodb",
//	"f2-spring:data:f2-spring-data-mongodb-test"
//)

include(
	"f2-spring:exception:f2-spring-boot-exception-http",
	"f2-spring:openapi:f2-spring-boot-openapi"
)

include(
	"f2-spring:function:f2-spring-boot-starter-function",
	"f2-spring:function:f2-spring-boot-starter-function-http",
	"f2-spring:function:f2-spring-boot-starter-function-rsocket",
	"f2-spring:function:f2-spring-boot-starter-observability-opentelemetry"
)

include(
	"f2-feature:catalog:f2-feature-catalog",
	"f2-feature:cloud-event-storming:f2-feature-cloud-event-storming",
	"f2-feature:version:f2-feature-version"
)

include(
	"sample:f2-sample-http",
	"sample:f2-sample-rsocket"
)
