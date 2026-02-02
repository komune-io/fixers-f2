package f2.dsl.cqrs.envelope

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Extension function to convert an object of type T into an Envelope containing the object.
 *
 * @param from The source envelope to copy properties from. Defaults to null.
 * @param source The source of the envelope. Defaults to the source of the `from` envelope or null.
 * @param id The ID of the envelope. Defaults to the ID of the `from` envelope or a randomly generated UUID.
 * @param type The type of the envelope. Defaults to the simple name of the class of T.
 * @param time The time of the envelope. Defaults to the time of the `from` envelope or null.
 * @param datacontenttype The data content type of the envelope. Defaults to the data content type of the
 *                          `from` envelope or null.
 * @return An Envelope containing the object.
 */
@OptIn(ExperimentalUuidApi::class)
inline fun <reified T> T.asEnvelope(
    from: Envelope<*>? = null,
    source: String? = from?.source,
    id: String = from?.id ?: Uuid.random().toString(),
    type: String = T::class.simpleName ?: "Unknown",
    time: String? = from?.time,
    datacontenttype: String? = from?.datacontenttype
): Envelope<T> {
    return asEnvelopeWithType(type, from, source, id, time, datacontenttype)
}


/**
 * Extension function to convert an object of type T into an Envelope containing the object with a specified type.
 *
 * @param type The type of the envelope.
 * @param from The source envelope to copy properties from. Defaults to null.
 * @param source The source of the envelope. Defaults to the source of the `from` envelope or null.
 * @param id The ID of the envelope. Defaults to the ID of the `from` envelope or a randomly generated UUID.
 * @param time The time of the envelope. Defaults to the time of the `from` envelope or null.
 * @param datacontenttype The data content type of the envelope.
 *      Defaults to the data content type of the `from` envelope or null.
 * @return An Envelope containing the object.
 */
@OptIn(ExperimentalUuidApi::class)
fun <T> T.asEnvelopeWithType(
    type: String,
    from: Envelope<*>? = null,
    source: String? = from?.source,
    id: String = from?.id ?: Uuid.random().toString(),
    time: String? = from?.time,
    datacontenttype: String? = from?.datacontenttype,
): Envelope<T> {
    return Envelope(
        specversion = "1.0",
        type = type,
        source = source,
        id = id,
        time = time,
        datacontenttype = datacontenttype,
        data = this
    )
}
