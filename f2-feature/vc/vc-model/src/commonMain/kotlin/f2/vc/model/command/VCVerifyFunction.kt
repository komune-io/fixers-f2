package f2.vc.model.command

import f2.dsl.cqrs.Command
import f2.dsl.fnc.F2Function
import f2.vc.model.VCGen
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

typealias VCVerifyFunction = F2Function<VCVerifyCommand, VCVerifyResult>

@Serializable
@JsExport
@JsName("VCVerifyCommand")
class VCVerifyCommand(
	val claims: VCGen,
) : Command

@Serializable
@JsExport
@JsName("VCVerifyResult")
class VCVerifyResult(
	val isValid: Boolean,
) : Command


object AnySerializer : KSerializer<Any> {
	override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Any")

	override fun serialize(encoder: Encoder, value: Any) {
		val jsonEncoder = encoder as JsonEncoder
		val jsonElement = serializeAny(value)
		jsonEncoder.encodeJsonElement(jsonElement)
	}


	private fun serializeAny(value: Any?): JsonElement = when (value) {
		is Map<*, *> -> {
			val mapContents = value.entries.associate { mapEntry ->
				mapEntry.key.toString() to serializeAny(mapEntry.value)
			}
			JsonObject(mapContents)
		}
		is List<*> -> {
			val arrayContents = value.map { listEntry -> serializeAny(listEntry) }
			JsonArray(arrayContents)
		}
		is Number -> JsonPrimitive(value)
		is Boolean -> JsonPrimitive(value)
		else -> JsonPrimitive(value.toString())
	}

	override fun deserialize(decoder: Decoder): Any {
		val jsonDecoder = decoder as JsonDecoder
		val element = jsonDecoder.decodeJsonElement()

		return deserializeJsonElement(element)
	}

	private fun deserializeJsonElement(element: JsonElement): Any = when (element) {
		is JsonObject -> {
			element.mapValues { deserializeJsonElement(it.value) }
		}
		is JsonArray -> {
			element.map { deserializeJsonElement(it) }
		}
		is JsonPrimitive -> element.toString()
	}
}
