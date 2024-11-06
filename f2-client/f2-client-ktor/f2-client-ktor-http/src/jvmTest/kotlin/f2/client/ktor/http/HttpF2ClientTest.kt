package f2.client.ktor.http

import f2.client.ktor.http.model.F2FilePart
import f2.client.ktor.http.plugin.F2Auth
import f2.client.domain.AuthRealmClientSecret
import f2.client.ktor.http.server.ServerClient
import f2.client.ktor.http.server.command.ServerConsumeCommand
import f2.client.ktor.http.server.command.ServerUploadCommand
import f2.client.ktor.http.server.command.ServerUploadCommandBody
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.jvm.javaio.toByteReadChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

class HttpF2ClientTest {

	@Test
	fun testBuilderExtensionFunctionApi(): Unit = runTest {
		HttpClientBuilder{
			install(Logging) {
				logger = Logger.DEFAULT
				level = LogLevel.ALL
			}
		}.build("http://localhost:8090")

		HttpClientBuilder.builder{
			install(Logging) {
				logger = Logger.DEFAULT
				level = LogLevel.ALL
			}
		}.build("http://localhost:8090")

		HttpClientBuilder{
			install(Logging) {
				logger = Logger.DEFAULT
				level = LogLevel.ALL
			}
		}.build("http://localhost:8090")

		HttpClientBuilder.default()
		HttpClientBuilder.generics {
			install(Logging) {
				logger = Logger.DEFAULT
				level = LogLevel.ALL
			}
			install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
				})
			}
		}.build("http://localhost:8090")
	}

//	@Test
	fun auth(): Unit = runTest {
		val client = HttpClient {
			install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
				})
			}
			install(Logging) {
				logger = Logger.DEFAULT
				level = LogLevel.ALL
			}
			install(F2Auth) {
				getAuth = {
					AuthRealmClientSecret(
						serverUrl = "http://keycloak-it:8080/auth",
						realmId = "boislocal-local",
						redirectUrl = "",
						clientId = "boislocal-api",
						clientSecret = "secret",
						isPublic = false

					)
				}
			}
		}

		val result = client.post("https://localhost:8090/fileList") {
			header("Content-Type", ContentType.Application.Json)
		}.bodyAsText()

		println(result)
	}

//	@Test
	fun test(): Unit = runTest {
		val client = ServerClient(
			client = HttpF2Client(
				httpClient = HttpClient(Java) {
					install(ContentNegotiation) {
						json(Json {
							ignoreUnknownKeys = true
						})
					}
				},
				"http://localhost:1000",
			)
		)
		val file = PathMatchingResourcePatternResolver().getResource("classpath:application.yml").contentAsByteArray

		val command = ServerUploadCommand(
			command = ServerUploadCommandBody("blblbl"),
			file = F2FilePart(
				name = "zefile",
				content = file.inputStream().toByteReadChannel()
			)
		)
		val result = client.uploadFile().invoke(flow { emit(command) }).first()

		println(result)
		Assertions.assertThat(result.base64).isEqualTo(file.toBase64())

		val consumed = client.consume().invoke(listOf(
			ServerConsumeCommand("1"),
			ServerConsumeCommand("2"),
			ServerConsumeCommand("3")
		).asFlow()).toList()
		println(consumed)
		Assertions.assertThat(consumed).hasSize(3)
	}
}
