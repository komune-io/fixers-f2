package f2.dsl.cqrs.envelope

/**
 * Interface representing an entity that contains an ID.
 */
interface WithEnvelopeId {
    /**
     * The ID of the envelope.
     */
    val id: String
}
