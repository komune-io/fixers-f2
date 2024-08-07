package f2.dsl.cqrs.envelope

/**
 * Interface representing an entity that contains data of type T.
 *
 * @param <T> The type of the data contained in the envelope.
 */
interface WithEnvelopeData<T> {
    /**
     * The data contained in the envelope.
     */
    val data: T
}
