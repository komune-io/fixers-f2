package f2.client.ktor.http

import f2.client.domain.AuthRealmClientSecret
import f2.client.ktor.http.model.F2FilePart
import f2.client.ktor.http.plugin.F2Auth
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
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

class HttpF2ClientTest {

    private val log = LoggerFactory.getLogger(HttpF2ClientTest::class.java)

    @Test
    fun testBuilderExtensionFunctionApi() {
        val _ = httpClientBuilderGenerics {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }.build("http://localhost:8090")

        val _ = httpClientBuilder {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }.build("http://localhost:8090")

        val _ = httpClientBuilderDefault()
        val _ = httpClientBuilderGenerics {
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

//  @Test
    suspend fun auth() {
        HttpClient {
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
        }.use { client ->
            val result = client.post("https://localhost:8090/fileList") {
                header("Content-Type", ContentType.Application.Json)
            }.bodyAsText()

            log.info("{}", result)
        }
    }

//  @Test
    suspend fun test() {
        HttpClient(Java) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }.use { httpClient ->
            val client = ServerClient(
                client = HttpF2Client(
                    httpClient = httpClient,
                    urlBase = "http://localhost:1000",
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

            log.info("{}", result)
            Assertions.assertThat(result.base64).isEqualTo(file.toBase64())

            val consumed = client.consume().invoke(listOf(
                ServerConsumeCommand("1"),
                ServerConsumeCommand("2"),
                ServerConsumeCommand("3")
            ).asFlow()).toList()
            log.info("{}", consumed)
            Assertions.assertThat(consumed).hasSize(3)
        }
    }
}
