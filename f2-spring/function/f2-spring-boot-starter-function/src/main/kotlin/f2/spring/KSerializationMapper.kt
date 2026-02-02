package f2.spring

import tools.jackson.databind.ObjectMapper
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.exception.F2Exception
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import java.util.UUID
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

/**
 * @author Dave Syer
 * @author Oleg Zhurakousky
 */
class KSerializationMapper(
    private val mapper: Json,
) : JsonMapper() {

    private val logger = LoggerFactory.getLogger(KSerializationMapper::class.java)

    companion object {
        val defaultJson = Json {
            ignoreUnknownKeys = true
        }
    }
    private val serializerCache: MutableMap<Type, KSerializer<Any>> =
        ConcurrentReferenceHashMap<Type, KSerializer<Any>>()

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("TooGenericExceptionCaught", "ThrowsCount")
    override fun <T> doFromJson(json: Any, type: Type): T {
        try {
            val serializerType = serializer(type)
            return when (json) {
                is String -> {
                    mapper.decodeFromString(serializerType, json) as T
                }
                is ByteArray -> {
                    mapper.decodeFromString(serializerType, String(json)) as T
                }
                is Reader -> {
                    mapper.decodeFromStream(serializerType, json.toInputStream()) as T
                }
                is JsonElement -> {
                    mapper.decodeFromJsonElement(serializerType, json) as T
                }
                else -> {
                    error("Failed to convert. Unknown type ${json::class.qualifiedName}")
                }
            }
        } catch (e: MissingFieldException) {
            throw F2Exception(error = F2Error(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis().toString(),
                message = "Missing parameter `${e.missingFields.joinToString(",")}`",
                code = 400,
            ), e)
        } catch (e: SerializationException) {
            throw F2Exception(error = F2Error(
                id = UUID.randomUUID().toString(),
                timestamp = System.currentTimeMillis().toString(),
                message = e.message!!,
                code = 400,
            ), e)
        } catch (e: Exception) {
            throw IllegalStateException(
                "Failed to convert. Possible bug as the conversion probably shouldn't have been attempted here",
                e
            )
        }
    }
    @Suppress("TooGenericExceptionCaught")
    override fun toJson(value: Any): ByteArray {
        val type = ResolvableType.forClass(value::class.java)
        val ser = serializer(type.type)
        var jsonBytes = super.toJson(value)
        if (jsonBytes == null) {
            try {
                jsonBytes = mapper.encodeToString(ser, value).toByteArray()
            } catch (e: Exception) {
                logger.debug("Ignored Error while serializing {}", value, e)
                // Mandatory to deserialize Collections.SingletonMap used by spring boot rsocket
                try {
                    jsonBytes = ObjectMapper().writeValueAsBytes(value)
                } catch (e: java.lang.Exception) {
                    logger.debug("Ignored Error while serializing {}", value, e)
                    //ignore and let other converters have a chance
                }
            }
        }
        return jsonBytes
    }

    override fun toString(value: Any): String {
        return try {
            mapper.encodeToString(value)
        } catch (e: SerializationException) {
            logger.debug("Ignored Error while serializing {}", value, e)
            return "Cannot convert to JSON"
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun serializer(type: Type): KSerializer<Any> {
        return serializerCache.getOrPut(type) {
            try {
                kotlinx.serialization.serializer(type)
            } catch (e: Exception) {
                logger.debug("Ignored Error while serializing {}", type, e)
                JsonElement.serializer() as KSerializer<Any>
            }
        }
    }

    @Throws(IOException::class)
    fun Reader.toInputStream(): InputStream {
        return this.use { initialReader ->
            val charBuffer = CharArray( size = 8 * 1024)
            val builder = StringBuilder()
            var numCharsRead: Int
            while (initialReader.read(charBuffer, 0, charBuffer.size).also { numCharsRead = it } != -1) {
                builder.appendRange(charBuffer, 0, numCharsRead)
            }
            ByteArrayInputStream(
                builder.toString().toByteArray(StandardCharsets.UTF_8)
            )
        }
    }
}
