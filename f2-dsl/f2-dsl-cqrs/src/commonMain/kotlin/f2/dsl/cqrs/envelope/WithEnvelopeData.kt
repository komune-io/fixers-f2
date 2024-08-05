package f2.dsl.cqrs.envelope

interface WithEnvelopeData<T> {
    val data: T
}
