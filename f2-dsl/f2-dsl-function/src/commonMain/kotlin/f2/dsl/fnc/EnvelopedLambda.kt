package f2.dsl.fnc

import f2.dsl.cqrs.envelope.Envelope


/**
 * Type alias for a function that takes an Envelope of type T and returns an Envelope of type R.
 */
typealias F2FunctionEnveloped<T, R> = F2Function<Envelope<T>, Envelope<R>>

/**
 * Type alias for a supplier that provides an Envelope of type R.
 */
typealias F2SupplierEnveloped<R> = F2Supplier<Envelope<R>>

/**
 * Type alias for a consumer that consumes an Envelope of type T.
 */
typealias F2ConsumerEnveloped<T> = F2Consumer<Envelope<T>>
