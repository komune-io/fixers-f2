package f2.dsl.cqrs.envelope

import f2.dsl.cqrs.Message
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable


@JsExport
@JsName("EnvelopeDTO")
interface EnvelopeDTO<T>: WithEnvelopeId, WithEnvelopeData<T> {
    override val id: String
    override val data: T
    val type: String
    val datacontenttype: String?
    val specversion: String?
    val source: String?
    val time: String?
}

@Serializable
@JsExport
@JsName("Envelope")
class Envelope<T>(
    override val id: String,
    override val data: T,
    override val type: String,
    override val datacontenttype: String?,
    override val specversion: String?,
    override val source: String?,
    override val time: String?
) : EnvelopeDTO<T>
