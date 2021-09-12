package f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SnapEvent")
open class SnapEvent<T>(
	override val eventType: String,
	override val eventTypeVersion: String? = null,
	override val cloudEventsVersion: String,
	override val source: String, // RFC 3986
	override val eventID: String,
	override val eventTime: String? = null, // RFC 3339
	override val schemaURL: String? = null,  // RFC 3986
	override val contentType: String? = null, // RFC 2046
	override val extensions: Map<String, Any>? = null,
	override val data: T,
) : CloudEvent<T>(
	eventType = eventType,
	eventTypeVersion = eventTypeVersion,
	cloudEventsVersion = cloudEventsVersion,
	source = source, // RFC 3986
	eventID = eventID,
	eventTime = eventTime, // RFC 3339
	schemaURL = schemaURL,  // RFC 3986
	contentType = contentType, // RFC 2046
	extensions = extensions,
	data = data
)
