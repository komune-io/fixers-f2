package f2.client.ktor

import f2.client.F2ClientType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class F2ClientBuilderTest {

    @Test
    fun `getHttp builds a client for http and https urls`() {
        assertThat(F2ClientBuilder.getHttp("http://localhost:8080").type).isEqualTo(F2ClientType.HTTP)
        assertThat(F2ClientBuilder.getHttp("https://localhost:8080").type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `getHttp rejects unsupported protocols`() {
        assertThatThrownBy { F2ClientBuilder.getHttp("ftp://localhost") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid Url")
    }

    @Test
    suspend fun `get builds a client for http and https urls`() {
        assertThat(F2ClientBuilder.get("http://localhost:8080").type).isEqualTo(F2ClientType.HTTP)
        assertThat(F2ClientBuilder.get("https://localhost:8080").type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    suspend fun `get rejects unsupported protocols`() {
        val thrown = runCatching { F2ClientBuilder.get("ftp://localhost") }.exceptionOrNull()

        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    suspend fun `get with config builds a client for http and https urls`() {
        var configured = false
        val client = F2ClientBuilder.get("http://localhost:8080") { configured = true }

        assertThat(client.type).isEqualTo(F2ClientType.HTTP)
        assertThat(configured).isTrue()

        assertThat(F2ClientBuilder.get("https://localhost:8080") { }.type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    suspend fun `get with config defaults to no configuration`() {
        val client = F2ClientBuilder.get(urlBase = "http://localhost:8080")

        assertThat(client.type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    suspend fun `get with config rejects unsupported protocols`() {
        val thrown = runCatching { F2ClientBuilder.get("ftp://localhost") { } }.exceptionOrNull()

        assertThat(thrown)
            .isInstanceOf(InvalidUrlException::class.java)
            .hasMessageContaining("is invalid")
    }
}
