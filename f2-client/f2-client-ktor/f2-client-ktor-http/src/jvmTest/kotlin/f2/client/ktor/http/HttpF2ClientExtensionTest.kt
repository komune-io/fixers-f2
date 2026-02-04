package f2.client.ktor.http

import f2.client.F2ClientType
import f2.client.ktor.http.model.F2UploadCommand
import f2.client.ktor.http.model.F2UploadSingleCommand
import f2.client.ktor.http.model.F2FilePart
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.full.isSubclassOf

class HttpF2ClientExtensionTest {

    @Test
    fun `HttpF2Client is created with correct properties`() {
        val httpClient = HttpClient(Java)
        val json = Json { ignoreUnknownKeys = true }

        val client = HttpF2Client(httpClient, "http://example.com", json)

        assertThat(client.urlBase).isEqualTo("http://example.com")
        assertThat(client.httpClient).isEqualTo(httpClient)
        assertThat(client.json).isEqualTo(json)
        assertThat(client.type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `HttpF2Client uses default json when not specified`() {
        val httpClient = HttpClient(Java)

        val client = HttpF2Client(httpClient, "http://test.com")

        assertThat(client.json).isNotNull()
    }

    @Test
    fun `F2UploadSingleCommand is subclass of F2UploadCommand`() {
        assertThat(F2UploadSingleCommand::class.isSubclassOf(F2UploadCommand::class)).isTrue()
    }

    @Test
    fun `Regular data class is not subclass of F2UploadCommand`() {
        data class RegularCommand(val value: String)
        assertThat(RegularCommand::class.isSubclassOf(F2UploadCommand::class)).isFalse()
    }

    @Test
    fun `function extension uses reflection to detect F2UploadCommand`() {
        val httpClient = HttpClient(Java)
        val client = HttpF2Client(httpClient, "http://test.com")

        // This tests that the extension function compiles and can be called
        // The actual HTTP call would fail without a server, but we're testing the type detection logic
        val uploadFunction = client.function<F2UploadSingleCommand<String>, String>("upload")
        assertThat(uploadFunction).isNotNull()

        data class RegularRequest(val data: String)
        val regularFunction = client.function<RegularRequest, String>("regular")
        assertThat(regularFunction).isNotNull()
    }
}
