package f2.client.ktor

import f2.client.F2ClientType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class F2ClientBuilderTest {

    @Test
    fun `get builds a client for http and https urls`() {
        assertThat(F2ClientBuilder.get("http://localhost:8080").type).isEqualTo(F2ClientType.HTTP)
        assertThat(F2ClientBuilder.get("https://localhost:8080").type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `get with config applies it`() {
        var isConfigured = false
        val client = F2ClientBuilder.get("http://localhost:8080") { isConfigured = true }

        assertThat(client.type).isEqualTo(F2ClientType.HTTP)
        assertThat(isConfigured).isTrue()
    }

    @Test
    fun `get rejects unsupported protocols, with or without config`() {
        listOf<() -> Unit>(
            { val _ = F2ClientBuilder.get("ftp://localhost") },
            { val _ = F2ClientBuilder.get("ftp://localhost") { } },
        ).forEach { get ->
            assertThatThrownBy { get() }
                .isInstanceOf(InvalidUrlException::class.java)
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageContaining("Invalid Url[ftp://localhost]")
        }
    }

    @Test
    fun `the get overloads that were suspend are gone from the binary`() {
        val gets = F2ClientBuilder::class.java.methods.filter { it.name.startsWith("get") && it.name != "getClass" }

        assertThat(gets.flatMap { it.parameterTypes.toList() })
            .noneMatch { it.name == "kotlin.coroutines.Continuation" }
        assertThat(runCatching { Class.forName("f2.client.ktor.GetKt") }.isFailure).isTrue()
    }
}
