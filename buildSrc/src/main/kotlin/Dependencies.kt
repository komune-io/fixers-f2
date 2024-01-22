import city.smartb.gradle.dependencies.FixersDependencies
import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add
import java.net.URI
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.repositories

object PluginVersions {
	val fixers = FixersPluginVersions.fixers
	val d2 = FixersPluginVersions.fixers
	const val kotlin = FixersPluginVersions.kotlin
	const val springBoot = FixersPluginVersions.springBoot
}

object Versions {
	object Kotlin {
		const val ktor = FixersVersions.Kotlin.ktor
	}

	object Json {
		const val jackson = FixersVersions.Json.jacksonKotlin
		const val gson = "2.10.1"
	}

	object CloudEvent {
        const val spring = "2.5.0"
    }
	object Spring {
		const val function = "4.1.0"
		const val security = FixersVersions.Spring.security
		const val boot = FixersVersions.Spring.boot
		const val framework = FixersVersions.Spring.framework
		const val data = FixersVersions.Spring.data
		const val slf4j = FixersVersions.Logging.slf4j
	}

	const val cucumber = FixersVersions.Test.cucumber
	const val springdoc = "1.6.11"
	const val rsocket = "0.15.4"
	const val embedMongo = "2.2.0"
	const val kotlinxDatetime = "0.4.0"
}

object Dependencies {
	object Mpp {
		fun rsocketKtorClient(scope: Scope) = scope.add(
			"io.rsocket.kotlin:rsocket-ktor-client:${Versions.rsocket}"
		)
		fun kotlinxDatetime(scope: Scope) = scope.add(
			"org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
		)
	}
	object Jvm {
		object Kotlin {
			fun coroutines(scope: Scope) = FixersDependencies.Jvm.Kotlin.coroutines(scope)
		}

		object Test {
			fun junit(scope: Scope) = FixersDependencies.Jvm.Test.junit(scope)
			fun springTest(scope: Scope) =  scope.add(
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
	maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots") }
	maven { url = URI("https://repo.spring.io/milestone") }
	mavenLocal()
}