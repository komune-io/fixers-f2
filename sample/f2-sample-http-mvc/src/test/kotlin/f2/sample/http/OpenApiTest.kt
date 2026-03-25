package f2.sample.http

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenApiTest {

    @LocalServerPort
    var port: Int = 0

    @Test
    fun `springdoc exposes f2 functions in api-docs`() {
        val body = RestClient.create("http://localhost:$port")
            .get()
            .uri("/v3/api-docs")
            .retrieve()
            .body(String::class.java)
        assertThat(body).contains("openapi")
        assertThat(body).contains("/sha256")
        assertThat(body).contains("/uuid")
        assertThat(body).contains("/uuids")
        assertThat(body).contains("/println")
    }
}
