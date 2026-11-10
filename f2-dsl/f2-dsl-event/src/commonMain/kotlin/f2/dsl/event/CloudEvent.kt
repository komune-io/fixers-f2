package f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * The attributes of a [CloudEvent] beside its identity and its data, as CloudEvents 0.1 names them.
 *
 * @property cloudEventsVersion the CloudEvents specification version the event follows
 * @property eventTypeVersion the version of [CloudEvent.eventType], set by its producer
 * @property eventTime when the event happened, RFC 3339
 * @property schemaURL the schema [CloudEvent.data] follows, RFC 3986
 * @property contentType the media type of [CloudEvent.data], RFC 2046
 * @property extensions the producer's own attributes, by name
 */
@JsExport
@JsName("CloudEventAttributes")
data class CloudEventAttributes(
    val cloudEventsVersion: String,
    val eventTypeVersion: String? = null,
    val eventTime: String? = null,
    val schemaURL: String? = null,
    val contentType: String? = null,
    val extensions: Map<String, String>? = null,
)

/**
 * An event of type [eventType], [eventID] at [source] (RFC 3986), carrying [data] and its [attributes].
 */
@JsExport
@JsName("CloudEvent")
open class CloudEvent<T>(
    open val eventType: String,
    open val source: String,
    open val eventID: String,
    open val attributes: CloudEventAttributes,
    open val data: T,
)
