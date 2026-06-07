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

inline fun <reified T> Flow<T>.mapToEnvelope(
    crossinline id: (T) -> String,
): Flow<Envelope<T>> = map { input ->
    input.asEnvelope(
        id = id(input),
    )
}

fun <T> Flow<T>.mapToEnvelope(
    type: String,
    id: (T) -> String,
): Flow<Envelope<T>> = map { input ->
    input.asEnvelopeWithType(
        id = id(input),
        type = type,
    )
}

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
