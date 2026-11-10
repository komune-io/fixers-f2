package org.springframework.cloud.function.context.config

import f2.spring.exception.UnreadableBodyException
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

    private fun json(payload: Any, contentType: String = "application/json"): Message<Any> =
        MessageBuilder.withPayload(payload).setHeader(MessageHeaders.CONTENT_TYPE, contentType).build()

    private fun assertRefused(payload: Any, contentType: String, cause: Class<out Throwable>) {
        assertThatThrownBy { converter.fromMessage(json(payload, contentType), Echo::class.java) }
            .isInstanceOfSatisfying(UnreadableBodyException::class.java) { error ->
                assertThat(error.error.code).isEqualTo(400)
                assertThat(error.error.refusal?.code).isEqualTo("http.INVALID_PAYLOAD")
            }
            .cause().isInstanceOf(cause)
    }

    private fun assertStatus(payload: Any, status: HttpStatus, reason: String, cause: Class<out Throwable>) {
        assertThatThrownBy { converter.fromMessage(json(payload), Echo::class.java) }
            .isInstanceOfSatisfying(ResponseStatusException::class.java) { error ->
                assertThat(error.statusCode).isEqualTo(status)
                assertThat(error.reason).isEqualTo(reason)
            }
            .cause().isInstanceOf(cause)
    }

    @Test
    fun `a truncated body is unreadable content and answers 400`() {
        val truncated = """{"name":"a","count":1""".toByteArray()
        assertRefused(truncated, "application/json", StreamReadException::class.java)
    }

    @Test
    fun `a body of the wrong shape is unreadable content and answers 400`() {
        val wrongShape = """{"name":"a","count":[]}""".toByteArray()
        assertRefused(wrongShape, "application/json", DatabindException::class.java)
    }

    @Test
    fun `a truncated body of any JSON media type is unreadable content and answers 400`() {
        val truncated = """{"name":"a","count":1""".toByteArray()
        listOf("application/cloudevents+json", "application/json; charset=UTF-8", "APPLICATION/JSON").forEach {
            assertRefused(truncated, it, StreamReadException::class.java)
        }
    }

    @Test
    fun `a body of a media type that is not JSON is left to the other converters`() {
        val truncated = """{"name":"a","count":1""".toByteArray()
        listOf("text/plain", "application/jsonp", "text/json").forEach {
            assertThat(converter.fromMessage(json(truncated, it), Echo::class.java)).`as`(it).isNull()
        }
    }

    @Test
    fun `a body whose read fails is a server error and answers 500`() {
        val failingReader = object : Reader() {
            override fun read(buffer: CharArray, offset: Int, length: Int): Int = throw IOException("read failed")
            override fun close() = Unit
        }
        assertStatus(
            failingReader, HttpStatus.INTERNAL_SERVER_ERROR, "Error reading json", JacksonIOException::class.java
        )
    }
}
