package city.smartb.f2.dsl.cqrs

data class CloudEvent<T>(
	val eventType: String,
	val eventTypeVersion: String? = null,
	val cloudEventsVersion: String,
	val source: String, // RFC 3986
	val eventID: String,
	val eventTime: String? = null, // RFC 3339
	val schemaURL: String? = null,  // RFC 3986
	val contentType: String? = null, // RFC 2046
	val extensions: Map<String, Any>? = null,
	val data: T
)