package f2.dsl.cqrs.envelope

import com.benasher44.uuid.uuid4

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
inline fun <reified T> T.asEnvelope(
    from: Envelope<*>? = null,
    source: String? = from?.source,
    id: String = from?.id ?: uuid4().toString(),
    type: String = T::class.simpleName ?: "Unknown",
    time: String? = from?.time,
    datacontenttype: String? = from?.datacontenttype
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
