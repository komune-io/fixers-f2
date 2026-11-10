package f2.dsl.cqrs.envelope

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * This object in an envelope typed by the simple name of [T].
 *
 * @param from The envelope whose `source`, `time` and `datacontenttype` the new one keeps. Defaults to null.
 * @param id The ID of the envelope. Defaults to the ID of the `from` envelope or a randomly generated UUID.
 * @param type The type of the envelope. Defaults to the simple name of the class of T.
 * @return An Envelope containing the object.
 */
@OptIn(ExperimentalUuidApi::class)
inline fun <reified T> T.asEnvelope(
    from: Envelope<*>? = null,
    id: String = from?.id ?: Uuid.random().toString(),
    type: String = T::class.simpleName ?: "Unknown",
): Envelope<T> = asEnvelopeWithType(type, from, id)

/**
 * This object in an envelope of the given [type].
 *
 * @param type The type of the envelope.
 * @param from The envelope whose `source`, `time` and `datacontenttype` the new one keeps. Defaults to null.
 * @param id The ID of the envelope. Defaults to the ID of the `from` envelope or a randomly generated UUID.
 * @return An Envelope containing the object.
 */
@OptIn(ExperimentalUuidApi::class)
fun <T> T.asEnvelopeWithType(
    type: String,
    from: Envelope<*>? = null,
    id: String = from?.id ?: Uuid.random().toString(),
): Envelope<T> = Envelope(
    type = type,
    source = from?.source,
    id = id,
    time = from?.time,
    datacontenttype = from?.datacontenttype,
    data = this,
)
