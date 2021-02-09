package city.smartb.f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("CloudEvent")
open class CloudEvent<T>(
	open val eventType: String,
	open val eventTypeVersion: String? = null,
	open val cloudEventsVersion: String,
	open val source: String, // RFC 3986
	open val eventID: String,
	open val eventTime: String? = null, // RFC 3339
	open val schemaURL: String? = null,  // RFC 3986
	open val contentType: String? = null, // RFC 2046
	open val extensions: Map<String, Any>? = null,
	open val data: T
)