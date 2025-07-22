import io.komune.fixers.gradle.dependencies.FixersDependencies
import io.komune.fixers.gradle.dependencies.FixersPluginVersions
import io.komune.fixers.gradle.dependencies.FixersVersions
import io.komune.fixers.gradle.dependencies.Scope
import io.komune.fixers.gradle.dependencies.add
import java.net.URI
import org.gradle.api.artifacts.dsl.RepositoryHandler

object PluginVersions {
	val fixers = FixersPluginVersions.fixers
	val d2 = FixersPluginVersions.fixers
	const val kotlin = FixersPluginVersions.kotlin
	const val springBoot = FixersPluginVersions.springBoot
}

object Versions {

	const val assertj = FixersVersions.Test.assertj

	object Kotlin {
		const val ktor = FixersVersions.Kotlin.ktor
		const val coroutines = FixersVersions.Kotlin.coroutines
		const val datetime = FixersVersions.Kotlin.datetime
	}

	object Json {
		const val jackson = FixersVersions.Json.jacksonKotlin
		const val gson = "2.11.0"
	}

	object CloudEvent {
        const val spring = "4.0.1"
    }
	object Spring {
		const val function = "4.1.3"
		const val security = FixersVersions.Spring.security
		const val boot = FixersVersions.Spring.boot
		const val framework = FixersVersions.Spring.framework
		const val data = FixersVersions.Spring.data
		const val slf4j = FixersVersions.Logging.slf4j
	}

	object Observability {
		const val micrometer = "1.2.2"
		const val micrometerOtlp = "1.12.3"
		const val opentelemetry = "1.34.1"
	}
	const val cucumber = FixersVersions.Test.cucumber
//	const val springdoc = "1.8.0"
	const val springdoc = "1.6.11"
	const val rsocket = "0.16.0"
	const val uuid = "0.8.4"
}

object Dependencies {
	object Mpp {

		object Ktor {
			fun clientFeatures(scope: Scope) = scope.add(
				"io.ktor:ktor-client-content-negotiation:${Versions.Kotlin.ktor}",
				"io.ktor:ktor-serialization-kotlinx-json:${Versions.Kotlin.ktor}",
				"io.ktor:ktor-client-logging:${Versions.Kotlin.ktor}"
			)

			fun client(commonMainApi: Scope, jvmScope: Scope, jsScope: Scope) {
				jvmScope.add(
					"io.ktor:ktor-client-java:${Versions.Kotlin.ktor}"
				)
				jsScope.add(
					"io.ktor:ktor-client-js:${Versions.Kotlin.ktor}"
				)
				clientFeatures(commonMainApi)
			}

			fun clientRsocket(scope: Scope) = scope.add(
				"io.rsocket.kotlin:rsocket-ktor-client:${Versions.rsocket}"
			)
		}

		fun kotlinxDatetime(scope: Scope) = scope.add(
			"org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlin.datetime}"
		)
		fun uuid(scope: Scope) = scope.add(
			"com.benasher44:uuid:${Versions.uuid}"
		)
	}

	object Jvm {
		object Kotlin {
			fun coroutines(scope: Scope) = FixersDependencies.Jvm.Kotlin.coroutines(scope)
		}

		object Test {
			fun junit(scope: Scope) = FixersDependencies.Jvm.Test.junit(scope)
			fun springTest(scope: Scope) = junit(scope).add(
				"org.springframework.boot:spring-boot-starter-test:${Versions.Spring.boot}"
			)
		}
		object Json {
			fun jackson(scope: Scope) = FixersDependencies.Jvm.Json.jackson(scope)
		}

		fun cucumber(scope: Scope) = FixersDependencies.Jvm.Test.cucumber(scope)

		object Spring {

			fun slf4J(scope: Scope) = FixersDependencies.Jvm.Logging.slf4j(scope)
			fun dataCommons(scope: Scope) = FixersDependencies.Jvm.Spring.dataCommons(scope)

			fun security(scope: Scope) = scope.add(
				"org.springframework.boot:spring-boot-starter-security:${Versions.Spring.boot}"
			)

			fun oauth2(scope: Scope) = scope.add(
				"org.springframework.security:spring-security-oauth2-resource-server:${Versions.Spring.security}",
				"org.springframework.security:spring-security-oauth2-jose:${Versions.Spring.security}"
			)

			fun autoconfigure(scope: Scope) = scope.add(
				"org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}"
			)


			fun configurationProcessor(scope: Scope) = scope.add(
				"org.springframework.boot:spring-boot-configuration-processor:${Versions.Spring.boot}"
			)

			fun cloudFunctionDep(scope: Scope) = scope.add(
				"com.google.code.gson:gson:${Versions.Json.gson}",
				"io.cloudevents:cloudevents-spring:${Versions.CloudEvent.spring}",
			)
			fun cloudFunction(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-context:${Versions.Spring.function}",
				"org.springframework.cloud:spring-cloud-function-kotlin:${Versions.Spring.function}",
				"org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}",
				"com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.Json.jackson}",
				"org.springframework:spring-web:${Versions.Spring.framework}",
			)

			fun cloudFunctionWebflux(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-starter-function-webflux:${Versions.Spring.function}"
			)

			fun cloudFunctionRSocket(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-rsocket:${Versions.Spring.function}"
			)
		}
	}
}

object Modules {
	object Bdd {
		val f2BddConfig = ":f2-bdd:f2-bdd-config"
		val f2BddSpringAutoconfigure = ":f2-bdd:f2-bdd-spring-autoconfigure"
		val f2BddSpringLambda = ":f2-bdd:f2-bdd-spring-lambda"
	}

	object Client {
		val f2ClientCore = ":f2-client:f2-client-core"
		val f2ClientKtor = ":f2-client:f2-client-ktor"
		val f2ClientKtorHttp = ":f2-client:f2-client-ktor:f2-client-ktor-http"
		val f2ClientKtorRSocket = ":f2-client:f2-client-ktor:f2-client-ktor-rsocket"
	}

	object Dsl {
		val f2DslFunction = ":f2-dsl:f2-dsl-function"
		val f2DslCqrs = ":f2-dsl:f2-dsl-cqrs"
		val f2DslEvent = ":f2-dsl:f2-dsl-event"
	}

	object Feature {
		val f2FeatureVcClient = ":f2-feature:f2-feature-vc-client"
		val f2FeatureVcFunction = ":f2-feature:f2-feature-vc-function"
		val f2FeatureVcModel = ":f2-feature:f2-feature-vc-model"

		val f2FeatureCatalog = ":f2-feature:catalog:f2-feature-catalog"
		val f2FeatureCloudEventStorming = ":f2-feature:cloud-event-storming:f2-feature-cloud-event-storming"
		val f2FeatureVersion = ":f2-feature:version:f2-feature-version"

	}

	object Spring {
		val f2SpringAuth = ":f2-spring:auth:f2-spring-boot-starter-auth"
		val f2SpringAuthKeycloak = ":f2-spring:auth:f2-spring-boot-starter-auth-keycloak"

		val f2SpringData = ":f2-spring:data:f2-spring-data"
		val f2SpringDataMongodb = ":f2-spring:data:f2-spring-data-mongodb"
		val f2SpringDataMongodbTest = ":f2-spring:data:f2-spring-data-mongodb-test"

		val f2SpringExceptionHttp = ":f2-spring:exception:f2-spring-boot-exception-http"
		val f2SpringOpenApi = ":f2-spring:openapi:f2-spring-boot-openapi"

		val f2SpringFunction = ":f2-spring:function:f2-spring-boot-starter-function"
		val f2SpringFunctionHttp = ":f2-spring:function:f2-spring-boot-starter-function-http"
		val f2SpringFunctionRSocket = ":f2-spring:function:f2-spring-boot-starter-function-rsocket"
	}

	object Sample {
		val f2SampleHttp = ":sample:f2-sample-http"
		val f2SampleRSocket = ":sample:f2-sample-rsocket"
	}

}

fun RepositoryHandler.defaultRepo() {
	mavenCentral()
	maven { url = URI("https://central.sonatype.com/repository/maven-snapshots") }
	maven { url = URI("https://repo.spring.io/milestone") }
	mavenLocal()
}
