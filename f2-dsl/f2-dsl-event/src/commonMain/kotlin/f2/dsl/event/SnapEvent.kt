package f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

/** A [CloudEvent] marking a state snapshot. */
@JsExport
@JsName("SnapEvent")
@Suppress("LongParameterList") // flat by CloudEvents spec, as CloudEvent
open class SnapEvent<T>(
    eventType: String,
    eventTypeVersion: String? = null,
    cloudEventsVersion: String,
    source: String,
    eventID: String,
    eventTime: String? = null,
    schemaURL: String? = null,
    contentType: String? = null,
    extensions: Map<String, String>? = null,
    data: T,
) : CloudEvent<T>(
    eventType = eventType,
    eventTypeVersion = eventTypeVersion,
    cloudEventsVersion = cloudEventsVersion,
    source = source,
    eventID = eventID,
    eventTime = eventTime,
    schemaURL = schemaURL,
    contentType = contentType,
    extensions = extensions,
    data = data,
)
