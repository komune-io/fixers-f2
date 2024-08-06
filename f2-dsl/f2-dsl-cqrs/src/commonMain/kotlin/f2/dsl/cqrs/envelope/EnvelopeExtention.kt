package f2.dsl.cqrs.envelope

import com.benasher44.uuid.uuid4

inline fun <reified T> T.asEnvelope(
    source: String? = null,
    id: String = uuid4().toString(),
    type: String =  T::class.simpleName ?: "Unknown",
    time: String? = null,
    datacontenttype: String? = null
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
