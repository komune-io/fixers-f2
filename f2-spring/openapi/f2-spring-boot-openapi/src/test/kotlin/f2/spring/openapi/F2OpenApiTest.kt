package f2.spring.openapi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestClient

@SpringBootTest(
    classes = [F2OpenApiTestApp::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
class F2OpenApiTest {

    @LocalServerPort
    var port: Int = 0

    private fun fetchApiDocs(): String {
        return RestClient.create("http://localhost:$port")
            .get()
            .uri("/v3/api-docs")
            .retrieve()
            .body(String::class.java)!!
    }

    @Test
    fun `f2 functions appear in openapi paths`() {
        val body = fetchApiDocs()
        assertThat(body).contains("/testFunction")
    }

    @Test
    fun `f2 suppliers appear in openapi paths`() {
        val body = fetchApiDocs()
        assertThat(body).contains("/testSupplier")
    }

    @Test
    fun `f2 consumers appear in openapi paths`() {
        val body = fetchApiDocs()
        assertThat(body).contains("/testConsumer")
    }

    @Test
    fun `f2 functions use POST method`() {
        val body = fetchApiDocs()
        assertThat(body).contains("\"testFunction_POST\"")
    }

    @Test
    fun `f2 suppliers use GET method`() {
        val body = fetchApiDocs()
        assertThat(body).contains("\"testSupplier_GET\"")
    }

    @Test
    fun `f2 consumers use POST method`() {
        val body = fetchApiDocs()
        assertThat(body).contains("\"testConsumer_POST\"")
    }
}
