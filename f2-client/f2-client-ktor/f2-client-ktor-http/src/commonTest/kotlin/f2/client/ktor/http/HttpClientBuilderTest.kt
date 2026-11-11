package f2.client.ktor.http

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Smoke coverage of the `HttpClientBuilder` factories. Running in `commonTest` means the
 * JS builder (`jsMain/JsHttpClientBuilder.kt`, backed by the Ktor Js engine) is exercised too, not only
 * the JVM one.
 */
class HttpClientBuilderTest {

    private val urlBase = "http://localhost:8080"

    @Test
    fun `httpClientBuilderDefault should build a client on the given base url`() {
        val client = httpClientBuilderDefault().build(urlBase)

        assertEquals(expected = urlBase, actual = client.urlBase)
    }

    @Test
    fun `httpClientBuilderGenerics should build a client without configuration`() {
        val client = httpClientBuilderGenerics().build(urlBase)

        assertEquals(expected = urlBase, actual = client.urlBase)
    }

    @Test
    fun `httpClientBuilderGenerics should apply the given configuration`() {
        var isConfigured = false

        val client = httpClientBuilderGenerics { isConfigured = true }.build(urlBase)

        assertTrue(isConfigured)
        assertEquals(expected = urlBase, actual = client.urlBase)
    }
}
