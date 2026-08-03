package f2.dsl.cqrs.envelope

import kotlin.js.JsExport

/**
 * Interface representing an entity that contains an ID.
 */
@JsExport
interface WithEnvelopeId {
    /**
     * The ID of the envelope.
     */
    val id: String
}
