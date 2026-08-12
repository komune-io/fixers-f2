package f2.client.ktor.http

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Smoke coverage of the `HttpClientBuilder` expect/actual pair. Running in `commonTest` means the
 * JS actual (`jsMain/HttpClientBuilder.kt`, backed by the Ktor Js engine) is exercised too, not only
 * the JVM one.
 */
class HttpClientBuilderTest {

    private val urlBase = "http://localhost:8080"

    @Test
    fun `httpClientBuilderDefault should build a client on the given base url`() {
        val client = httpClientBuilderDefault().build(urlBase)

        assertEquals(urlBase, client.urlBase)
    }

    @Test
    fun `httpClientBuilderGenerics should build a client without configuration`() {
        val client = httpClientBuilderGenerics().build(urlBase)

        assertEquals(urlBase, client.urlBase)
    }

    @Test
    fun `httpClientBuilderGenerics should apply the given configuration`() {
        var configured = false

        val client = httpClientBuilderGenerics { configured = true }.build(urlBase)

        assertTrue(configured)
        assertEquals(urlBase, client.urlBase)
    }
}
