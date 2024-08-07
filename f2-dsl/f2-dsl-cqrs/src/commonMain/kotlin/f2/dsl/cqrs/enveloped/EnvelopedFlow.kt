package f2.dsl.cqrs.enveloped

import f2.dsl.cqrs.envelope.Envelope
import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a Flow of Envelopes containing data of type T.
 *
 * @param <T> The type of the data contained in the envelopes.
 */
typealias EnvelopedFlow<T> = Flow<Envelope<T>>
