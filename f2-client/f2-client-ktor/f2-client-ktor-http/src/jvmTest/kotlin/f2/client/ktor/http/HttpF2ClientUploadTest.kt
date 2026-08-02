package f2.client.ktor.http

import f2.client.ktor.http.model.F2FilePart
import f2.client.ktor.http.model.F2UploadCommand
import f2.client.ktor.http.model.F2UploadMultipleCommand
import f2.client.ktor.http.model.F2UploadSingleCommand
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.toByteArray
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import kotlin.reflect.typeOf
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Serializable
data class UploadTestBody(val someData: String)

@Serializable
data class UploadTestEvent(val ok: Boolean)

class HttpF2ClientUploadTest {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private class RecordedRequest {
        var contentType: String? = null
        var body: String? = null
    }

    private fun mockClient(recorded: RecordedRequest): HttpClient = HttpClient(MockEngine { request ->
        recorded.contentType = request.body.contentType?.toString()
        recorded.body = request.body.toByteArray().decodeToString()
        respond(
            content = """{"ok":true}""",
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        )
    }) {
        install(ContentNegotiation) {
            json(json)
        }
    }

    private fun uploadFunction(client: HttpF2Client) = client.function<F2UploadCommand<UploadTestBody>, UploadTestEvent>(
        route = "upload",
        queryTypeInfo = TypeInfo(F2UploadCommand::class, typeOf<UploadTestBody>()),
        responseTypeInfo = TypeInfo(UploadTestEvent::class, typeOf<UploadTestEvent>())
    )

    @Test
    suspend fun `function posts single upload command as multipart form data`() {
        val recorded = RecordedRequest()
        val client = HttpF2Client(mockClient(recorded), "http://localhost", json)

        val command = F2UploadSingleCommand(
            command = UploadTestBody("hello-upload"),
            file = F2FilePart(
                name = "file.txt",
                content = ByteReadChannel("file-content"),
                contentType = "text/plain"
            )
        )

        val results = uploadFunction(client).invoke(flowOf(command)).toList()

        assertThat(results).containsExactly(UploadTestEvent(ok = true))
        assertThat(recorded.contentType).startsWith("multipart/form-data")
        assertThat(recorded.body)
            .contains("someData")
            .contains("hello-upload")
            .contains("filename=file.txt")
            .contains("file-content")
            .contains("text/plain")
    }

    @Test
    suspend fun `function posts multiple upload files in one multipart request`() {
        val recorded = RecordedRequest()
        val client = HttpF2Client(mockClient(recorded), "http://localhost", json)

        val command = F2UploadMultipleCommand(
            command = UploadTestBody("multi-upload"),
            files = listOf(
                F2FilePart(name = "first.txt", content = ByteReadChannel("first-content"), contentType = "text/plain"),
                F2FilePart(name = "second.bin", content = ByteReadChannel("second-content")),
            )
        )

        val results = uploadFunction(client).invoke(flowOf(command)).toList()

        assertThat(results).containsExactly(UploadTestEvent(ok = true))
        assertThat(recorded.contentType).startsWith("multipart/form-data")
        assertThat(recorded.body)
            .contains("multi-upload")
            .contains("filename=first.txt")
            .contains("first-content")
            .contains("filename=second.bin")
            .contains("second-content")
    }
}
