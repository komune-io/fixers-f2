pluginManagement {
	repositories {
		gradlePluginPortal()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
	}
}

rootProject.name = "f2"

include(
	"f2-client",
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
	"f2-spring:data:f2-spring-data",
	"f2-spring:data:f2-spring-data-mongodb",
	"f2-spring:data:f2-spring-data-mongodb-test"
)

//include(
//	"f2-feature:vc:vc-client",
//	"f2-feature:vc:vc-function",
//	"f2-feature:vc:vc-model"
//)

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

//include(
//	"sample:f2-sample-http",
//	"sample:f2-sample-rsocket"
//)
