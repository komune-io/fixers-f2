package org.springframework.cloud.function.context.config

import java.io.IOException
import java.io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.json.JacksonMapper
import org.springframework.http.HttpStatus
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.server.ResponseStatusException
import tools.jackson.core.exc.JacksonIOException
import tools.jackson.core.exc.StreamReadException
import tools.jackson.databind.DatabindException
import tools.jackson.databind.json.JsonMapper

class JsonMessageConverterTest {

    class Echo {
        var name: String = ""
        var count: Int = 0
    }

    private val converter = JsonMessageConverter(JacksonMapper(JsonMapper.builder().build()))

    private fun json(payload: Any): Message<Any> = MessageBuilder.withPayload(payload)
        .setHeader(MessageHeaders.CONTENT_TYPE, "application/json")
        .build()

    private fun assertStatus(payload: Any, status: HttpStatus, cause: Class<out Throwable>) {
        assertThatThrownBy { converter.fromMessage(json(payload), Echo::class.java) }
            .isInstanceOfSatisfying(ResponseStatusException::class.java) { error ->
                assertThat(error.statusCode).isEqualTo(status)
            }
            .cause().isInstanceOf(cause)
    }

    @Test
    fun `a truncated body is unreadable content and answers 400`() {
        assertStatus("""{"name":"a","count":1""".toByteArray(), HttpStatus.BAD_REQUEST, StreamReadException::class.java)
    }

    @Test
    fun `a body of the wrong shape is unreadable content and answers 400`() {
        assertStatus("""{"name":"a","count":[]}""".toByteArray(), HttpStatus.BAD_REQUEST, DatabindException::class.java)
    }

    @Test
    fun `a body whose read fails is a server error and answers 500`() {
        val failingReader = object : Reader() {
            override fun read(buffer: CharArray, offset: Int, length: Int): Int = throw IOException("read failed")
            override fun close() = Unit
        }
        assertStatus(failingReader, HttpStatus.INTERNAL_SERVER_ERROR, JacksonIOException::class.java)
    }
}
