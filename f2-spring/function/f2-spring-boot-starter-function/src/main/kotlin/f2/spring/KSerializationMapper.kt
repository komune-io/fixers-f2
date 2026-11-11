package f2.spring

import f2.dsl.cqrs.error.invalidPayloadError
import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.exception.F2Exception
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromStream
import org.slf4j.LoggerFactory
import org.springframework.cloud.function.json.JsonMapper
import org.springframework.core.ResolvableType
import org.springframework.util.ConcurrentReferenceHashMap
import tools.jackson.core.JacksonException
import tools.jackson.databind.ObjectMapper

/**
 * @author Dave Syer
 * @author Oleg Zhurakousky
 */
class KSerializationMapper(
    private val mapper: Json,
) : JsonMapper() {

    private val logger = LoggerFactory.getLogger(KSerializationMapper::class.java)

    private val serializerCache: MutableMap<Type, KSerializer<Any>> =
        ConcurrentReferenceHashMap<Type, KSerializer<Any>>()

    @OptIn(ExperimentalSerializationApi::class)
    override fun <T> doFromJson(json: Any, type: Type): T {
        val decoded = try {
            decode(json, type)
        } catch (e: SerializationException) {
            // a body the caller sent: missing a required field, or not readable as the type
            val error = (e as? MissingFieldException)?.let { missingParameterError(it.missingFields.joinToString(",")) }
                ?: invalidPayloadError("Unreadable request body")
            throw F2Exception(error, e)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException(
                "Failed to convert. Possible bug as the conversion probably shouldn't have been attempted here",
                e
            )
        }
        @Suppress("UNCHECKED_CAST") // T is erased: the serializer of [type] decided what decoded is
        return decoded as T
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun decode(json: Any, type: Type): Any {
        val serializerType = serializer(type)
        return when (json) {
            is String -> mapper.decodeFromString(serializerType, json)
            is ByteArray -> mapper.decodeFromString(serializerType, String(json))
            is Reader -> json.toInputStream().use { mapper.decodeFromStream(serializerType, it) }
            is JsonElement -> mapper.decodeFromJsonElement(serializerType, json)
            else -> error("Failed to convert. Unknown type ${json::class.java.name}")
        }
    }

    override fun toJson(value: Any): ByteArray? = super.toJson(value) ?: try {
        mapper.encodeToString(serializer(ResolvableType.forClass(value::class.java).type), value).toByteArray()
    } catch (e: SerializationException) {
        logger.debug(LOG_SERIALIZATION_ERROR, value, e)
        jacksonFallback(value)
    } catch (e: IllegalArgumentException) {
        logger.debug(LOG_SERIALIZATION_ERROR, value, e)
        jacksonFallback(value)
    }

    // Mandatory to serialize Collections.SingletonMap used by spring boot rsocket; null lets other converters try.
    private fun jacksonFallback(value: Any): ByteArray? = try {
        ObjectMapper().writeValueAsBytes(value)
    } catch (e: JacksonException) {
        logger.debug(LOG_SERIALIZATION_ERROR, value, e)
        null
    }

    override fun toString(value: Any): String {
        return try {
            mapper.encodeToString(value)
        } catch (e: SerializationException) {
            logger.debug(LOG_SERIALIZATION_ERROR, value, e)
            return "Cannot convert to JSON"
        }
    }

    private fun serializer(type: Type): KSerializer<Any> {
        return serializerCache.getOrPut(type) {
            kotlinx.serialization.serializer(type)
        }
    }

    @Throws(IOException::class)
    fun Reader.toInputStream(): InputStream {
        return this.use { initialReader ->
            val charBuffer = CharArray( size = 8 * 1024)
            val builder = StringBuilder()
            var numCharsRead: Int
            while (initialReader.read(charBuffer, 0, charBuffer.size).also { numCharsRead = it } != -1) {
                builder.appendRange(value = charBuffer, startIndex = 0, endIndex = numCharsRead)
            }
            ByteArrayInputStream(
                builder.toString().toByteArray(StandardCharsets.UTF_8)
            )
        }
    }

    companion object {
        val defaultJson = Json {
            ignoreUnknownKeys = true
        }
        private const val LOG_SERIALIZATION_ERROR = "Ignored Error while serializing {}"
    }
}
