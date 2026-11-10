package f2.dsl.event

import kotlin.js.JsExport
import kotlin.js.JsName

/**
 * An event, with the CloudEvents 0.1 attributes.
 *
 * @param T the type of [data]
 * @property eventType the type of the event
 * @property eventTypeVersion the version of [eventType], set by its producer
 * @property cloudEventsVersion the CloudEvents specification version the event follows
 * @property source the producer, RFC 3986
 * @property eventID the id of the event at its [source]
 * @property eventTime when the event happened, RFC 3339
 * @property schemaURL the schema [data] follows, RFC 3986
 * @property contentType the media type of [data], RFC 2046
 * @property extensions the producer's own attributes, by name
 * @property data the event's payload
 */
@JsExport
@JsName("CloudEvent")
@Suppress("LongParameterList") // flat by CloudEvents spec: every attribute sits beside the others on the wire
open class CloudEvent<T>(
    open val eventType: String,
    open val eventTypeVersion: String? = null,
    open val cloudEventsVersion: String,
    open val source: String,
    open val eventID: String,
    open val eventTime: String? = null,
    open val schemaURL: String? = null,
    open val contentType: String? = null,
    open val extensions: Map<String, String>? = null,
    open val data: T,
)
