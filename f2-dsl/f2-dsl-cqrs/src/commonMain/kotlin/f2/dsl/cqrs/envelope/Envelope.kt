package f2.dsl.cqrs.envelope

import f2.dsl.cqrs.Message
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@JsExport
@JsName("EnvelopeDTO")
interface EnvelopeDTO<T>: WithEnvelopeId, WithEnvelopeData<T>, Message {
    override val id: String
    override val data: T
    val type: String
    val datacontenttype: String?
    val specversion: String?
    val source: String?
    val time: String?
}

/** The CloudEvents version every envelope follows. */
const val ENVELOPE_SPEC_VERSION: String = "1.0"

/** A CloudEvents envelope: its [specversion] is [ENVELOPE_SPEC_VERSION], the one version F2 writes and reads. */
@Serializable
@JsExport
@JsName("Envelope")
data class Envelope<T>(
    override val id: String,
    override val data: T,
    override val type: String,
    override val datacontenttype: String? = null,
    override val source: String? = null,
    override val time: String? = null
) : EnvelopeDTO<T> {
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) // on the wire whatever the Json's encodeDefaults says
    override val specversion: String = ENVELOPE_SPEC_VERSION
}

