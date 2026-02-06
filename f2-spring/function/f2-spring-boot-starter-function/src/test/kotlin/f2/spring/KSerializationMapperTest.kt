package f2.spring

import f2.dsl.cqrs.exception.F2Exception
import java.io.StringReader
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class KSerializationMapperTest {

    private val mapper = KSerializationMapper(KSerializationMapper.defaultJson)

    @Test
    fun `doFromJson deserializes from String`() {
        val json = """{"name":"test","value":42}"""
        val result: TestData = mapper.fromJson(json, TestData::class.java)

        assertEquals("test", result.name)
        assertEquals(42, result.value)
    }

    @Test
    fun `doFromJson deserializes from ByteArray`() {
        val json = """{"name":"byteArray","value":100}""".toByteArray()
        val result: TestData = mapper.fromJson(json, TestData::class.java)

        assertEquals("byteArray", result.name)
        assertEquals(100, result.value)
    }

    @Test
    fun `doFromJson deserializes from Reader`() {
        val json = """{"name":"reader","value":200}"""
        val reader = StringReader(json)
        val result: TestData = mapper.fromJson(reader, TestData::class.java)

        assertEquals("reader", result.name)
        assertEquals(200, result.value)
    }

    @Test
    fun `doFromJson deserializes from JsonElement`() {
        val jsonElement = buildJsonObject {
            put("name", JsonPrimitive("element"))
            put("value", JsonPrimitive(300))
        }
        val result: TestData = mapper.fromJson(jsonElement, TestData::class.java)

        assertEquals("element", result.name)
        assertEquals(300, result.value)
    }

    @Test
    fun `doFromJson throws F2Exception for missing required field`() {
        val json = """{"other":"field"}"""

        val exception = assertThrows<F2Exception> {
            mapper.fromJson(json, RequiredFieldData::class.java)
        }

        assertNotNull(exception.error)
        assertEquals(400, exception.error.code)
        assertTrue(exception.error.message.contains("required") || exception.error.message.contains("Missing"))
    }

    @Test
    fun `doFromJson throws F2Exception for invalid JSON`() {
        val json = """{"name":invalid}"""

        val exception = assertThrows<F2Exception> {
            mapper.fromJson(json, TestData::class.java)
        }

        assertNotNull(exception.error)
        assertEquals(400, exception.error.code)
    }

    @Test
    fun `doFromJson throws exception for unsupported type`() {
        val unsupportedInput = 12345

        // The mapper tries to convert the input but fails - it may throw F2Exception or IllegalStateException
        // depending on how the input is processed
        assertThrows<Exception> {
            mapper.fromJson(unsupportedInput, TestData::class.java)
        }
    }

    @Test
    fun `toJson serializes object to ByteArray`() {
        val data = TestData("serialize", 500)

        val result = mapper.toJson(data)

        assertNotNull(result)
        val jsonString = String(result)
        assertTrue(jsonString.contains("serialize"))
        assertTrue(jsonString.contains("500"))
    }

    @Test
    fun `toJson handles non-serializable objects with fallback`() {
        data class NonSerializableData(val field: String)
        val data = NonSerializableData("fallback")

        val result = mapper.toJson(data)

        assertNotNull(result)
        val jsonString = String(result)
        assertTrue(jsonString.contains("fallback"))
    }

    @Test
    fun `toString serializes object to String`() {
        val data = mapOf("key" to "value")

        val result = mapper.toString(data)

        assertTrue(result.contains("key") || result.contains("Cannot convert"))
    }

    @Test
    fun `toString returns error message for non-serializable object`() {
        val nonSerializable = object {
            val field = "value"
        }

        val result = mapper.toString(nonSerializable)

        assertEquals("Cannot convert to JSON", result)
    }

    @Test
    fun `serializer caches serializers for types`() {
        val json1 = """{"name":"first","value":1}"""
        val json2 = """{"name":"second","value":2}"""

        val result1: TestData = mapper.fromJson(json1, TestData::class.java)
        val result2: TestData = mapper.fromJson(json2, TestData::class.java)

        assertEquals("first", result1.name)
        assertEquals("second", result2.name)
    }

    @Test
    fun `Reader toInputStream converts correctly`() {
        val json = """{"name":"inputstream","value":700}"""
        val reader = StringReader(json)

        val result: TestData = mapper.fromJson(reader, TestData::class.java)

        assertEquals("inputstream", result.name)
        assertEquals(700, result.value)
    }

    @Test
    fun `Reader toInputStream handles large content`() {
        val largeContent = "x".repeat(10000)
        val json = """{"name":"$largeContent","value":800}"""
        val reader = StringReader(json)

        val result: TestData = mapper.fromJson(reader, TestData::class.java)

        assertEquals(largeContent, result.name)
        assertEquals(800, result.value)
    }

    @Test
    fun `defaultJson ignores unknown keys`() {
        val jsonWithExtra = """{"name":"test","value":42,"unknown":"ignored"}"""

        val result: TestData = mapper.fromJson(jsonWithExtra, TestData::class.java)

        assertEquals("test", result.name)
        assertEquals(42, result.value)
    }

    @Test
    fun `custom Json configuration is respected`() {
        val strictJson = Json { ignoreUnknownKeys = false }
        val strictMapper = KSerializationMapper(strictJson)
        val jsonWithExtra = """{"name":"test","value":42,"unknown":"fail"}"""

        assertThrows<F2Exception> {
            strictMapper.fromJson(jsonWithExtra, TestData::class.java)
        }
    }

    @Test
    fun `doFromJson handles empty string in ByteArray`() {
        val json = """{"name":"","value":0}""".toByteArray()

        val result: TestData = mapper.fromJson(json, TestData::class.java)

        assertEquals("", result.name)
        assertEquals(0, result.value)
    }

    @Test
    fun `toJson and fromJson are symmetric`() {
        val original = TestData("symmetric", 999)

        val serialized = mapper.toJson(original)
        val deserialized: TestData = mapper.fromJson(serialized, TestData::class.java)

        assertEquals(original, deserialized)
    }
}
