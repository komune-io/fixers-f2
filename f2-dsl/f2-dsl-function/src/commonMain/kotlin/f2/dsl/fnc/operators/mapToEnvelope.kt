package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.Envelope
import f2.dsl.cqrs.envelope.asEnvelope
import f2.dsl.cqrs.envelope.asEnvelopeWithType
import f2.dsl.fnc.F2Function
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

// =====================================================================================
// Required-id variants — the caller decides what the envelope id is.
//
// The previous default `{ Uuid.random().toString() }` silently severed any caller-side
// correlation between input commands and resulting outcomes (the envelope id flows
// through s2's PersistOutcome.msgId). Callers that need fire-and-forget random ids
// must now opt in explicitly via `mapToEnvelopeWithRandomId(...)`.
// =====================================================================================

/**
 * Maps each input through this [F2Function] and wraps the result in an [Envelope]
 * whose id is derived from the input via [id]. Use this when the caller needs to
 * correlate outputs back to inputs (any pipeline that surfaces per-msgId failure
 * routing).
 */
inline fun <T, reified R> F2Function<T, R>.mapToEnvelope(
    crossinline id: (T) -> String,
): F2Function<T, Envelope<R>> = F2Function { inputs: Flow<T> ->
    inputs.map { input ->
        val resultFlow = this.invoke(flowOf(input))
        resultFlow.map { result ->
            result.asEnvelope(
                id = id(input),
            )
        }.first()
    }
}

/**
 * Wraps each element of this [Flow] in an [Envelope] whose id is derived via [id].
 */
inline fun <reified T> Flow<T>.mapToEnvelope(
    crossinline id: (T) -> String,
): Flow<Envelope<T>> = map { input ->
    input.asEnvelope(
        id = id(input),
    )
}

/**
 * Wraps each element of this [Flow] in an [Envelope] with the given [type], whose id
 * is derived via [id].
 */
fun <T> Flow<T>.mapToEnvelope(
    type: String,
    id: (T) -> String,
): Flow<Envelope<T>> = map { input ->
    input.asEnvelopeWithType(
        id = id(input),
        type = type,
    )
}

// =====================================================================================
// Explicit random-id opt-in variants — for fire-and-forget cases (output wrappings,
// anonymous transforms) where caller-side correlation is not needed.
// =====================================================================================

@OptIn(ExperimentalUuidApi::class)
inline fun <T, reified R> F2Function<T, R>.mapToEnvelopeWithRandomId(): F2Function<T, Envelope<R>> =
    mapToEnvelope { Uuid.random().toString() }

@OptIn(ExperimentalUuidApi::class)
inline fun <reified T> Flow<T>.mapToEnvelopeWithRandomId(): Flow<Envelope<T>> =
    mapToEnvelope { Uuid.random().toString() }

@OptIn(ExperimentalUuidApi::class)
fun <T> Flow<T>.mapToEnvelopeWithRandomId(
    type: String,
): Flow<Envelope<T>> = mapToEnvelope(type) { Uuid.random().toString() }

// =====================================================================================
// Envelope-preserving transformations — unchanged.
// =====================================================================================

suspend inline fun <T, reified R> Envelope<T>.mapEnvelope(
    crossinline transform: suspend (value: T) -> R,
    source: String? = null,
    id: String = this.id,
    type: String = R::class.simpleName ?: "Unknown",
    time: String? = null,
    datacontenttype: String? = null
): Envelope<R> {
    return transform(data).asEnvelope(this, source, id, type, time, datacontenttype)
}


suspend fun <T, R> Envelope<T>.mapEnvelopeWithType(
    transform: suspend (value: T) -> R,
    type: String,
    source: String? = null,
    id: String = this.id,
    time: String? = null,
    datacontenttype: String? = null
): Envelope<R> {
    return transform(data).asEnvelopeWithType(type, this, source, id, time, datacontenttype)
}


inline fun <T, reified R> Flow<Envelope<T>>.mapEnvelopesReified(
    crossinline transform: suspend (value: T) -> R
): Flow<Envelope<R>> = map { input ->
    input.mapEnvelope(transform)
}

fun <T, R> Flow<Envelope<T>>.mapEnvelopes(
    transform: suspend (value: T) -> R,
    type: String
): Flow<Envelope<R>> = map { input ->
    input.mapEnvelopeWithType(transform, type)
}
