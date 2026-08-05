package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.messaging.converter.MessageConversionException
import tools.jackson.module.kotlin.KotlinInvalidNullException
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue

class F2ExceptionHandlerTest {

    private val handler = F2ExceptionHandler()

    data class Sample(val name: String)

    @Test
    fun `handleF2Exception should map error code to http status`() {
        val exception = F2Exception(message = "User not found", id = "error-id", code = 404)

        val response = handler.handleF2Exception(exception)

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.body!!["id"]).isEqualTo("error-id")
        assertThat(response.body!!["code"]).isEqualTo(404)
        assertThat(response.body!!["message"]).isEqualTo("User not found")
        assertThat(response.body!!["timestamp"]).isNotNull
    }

    @Test
    fun `handleF2Exception should default to internal server error for unknown code`() {
        val exception = F2Exception(message = "Broken", code = 999)

        val response = handler.handleF2Exception(exception)

        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @Test
    fun `handleKotlinNullException should return bad request with missing parameter message`() {
        val exception = kotlinInvalidNullException()

        val response = handler.handleKotlinNullException(exception)

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body!!["code"]).isEqualTo(400)
        assertThat(response.body!!["message"]).isEqualTo("Missing parameter `name`")
        assertThat(response.body!!["id"]).isNotNull
        assertThat(response.body!!["timestamp"]).isNotNull
    }

    @Test
    fun `handleMessageConversionException should return bad request with parsed message`() {
        val exception = MessageConversionException("Error parsing json", RuntimeException("bad json"))

        val response = handler.handleMessageConversionException(exception)

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body!!["code"]).isEqualTo(400)
        assertThat(response.body!!["message"]).isEqualTo("Error parsing json")
        assertThat(response.body!!["id"]).isNotNull
        assertThat(response.body!!["timestamp"]).isNotNull
    }

    private fun kotlinInvalidNullException(): KotlinInvalidNullException {
        var exception: KotlinInvalidNullException? = null
        assertThatThrownBy {
            jacksonObjectMapper().readValue<Sample>("""{"name":null}""")
        }.isInstanceOf(KotlinInvalidNullException::class.java)
            .satisfies({ thrown -> exception = thrown as KotlinInvalidNullException })
        return exception!!
    }
}
