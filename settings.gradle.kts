pluginManagement {
	repositories {
		gradlePluginPortal()
		maven { url = uri("https://oss.sonatype.org/service/local/repositories/releases/content") }
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
		mavenLocal()
	}
}

rootProject.name = "f2"

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
)

include(
	"f2-spring:data:f2-spring-data",
	"f2-spring:data:f2-spring-data-mongodb",
	"f2-spring:data:f2-spring-data-mongodb-test"
)

include(
	"f2-spring:exception:f2-spring-boot-exception-http",
	"f2-spring:openapi:f2-spring-boot-openapi"
)

include(
	"f2-spring:function:f2-spring-boot-starter-function",
	"f2-spring:function:f2-spring-boot-starter-function-http",
	"f2-spring:function:f2-spring-boot-starter-function-rsocket"
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
