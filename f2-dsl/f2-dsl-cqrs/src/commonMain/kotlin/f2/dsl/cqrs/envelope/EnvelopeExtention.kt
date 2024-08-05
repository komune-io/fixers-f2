package f2.dsl.cqrs.envelope

import f2.dsl.cqrs.Message
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

inline fun <reified T : Message> T.asEnvelope(
    source: String,
    id: String,
    data: T,
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
        data = data
    )
}
