package f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

/** A [CloudEvent] marking a state snapshot. */
@JsExport
@JsName("SnapEvent")
open class SnapEvent<T>(
    eventType: String,
    source: String,
    eventID: String,
    attributes: CloudEventAttributes,
    data: T,
) : CloudEvent<T>(
    eventType = eventType,
    source = source,
    eventID = eventID,
    attributes = attributes,
    data = data,
)
