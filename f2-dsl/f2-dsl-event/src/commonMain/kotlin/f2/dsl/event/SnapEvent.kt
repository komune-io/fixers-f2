package f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("SnapEvent")
open class SnapEvent<T> (
	eventType: String,
	eventTypeVersion: String? = null,
	cloudEventsVersion: String,
	source: String, // RFC 3986
	eventID: String,
	eventTime: String? = null, // RFC 3339
	schemaURL: String? = null,  // RFC 3986
	contentType: String? = null, // RFC 2046
	extensions: Map<String, Any>? = null,
	data: T,
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
