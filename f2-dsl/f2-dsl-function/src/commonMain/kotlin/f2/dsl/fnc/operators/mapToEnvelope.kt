package f2.dsl.fnc.operators

import com.benasher44.uuid.uuid4
import f2.dsl.cqrs.envelope.Envelope
import f2.dsl.cqrs.envelope.asEnvelope
import f2.dsl.cqrs.envelope.asEnvelopeWithType
import f2.dsl.fnc.F2Function
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


//fun <T, R> F2Function<T, R>.wrap(): F2Function<Envelope<T>, Envelope<R>> {
//    return F2Function { envelopes: Flow<Envelope<T>> ->
//        envelopes.map { envelope ->
//            val resultFlow = this.invoke(flowOf(envelope.data))
//            resultFlow.map { result ->
//                Envelope(
//                    id = envelope.id,
//                    data = result,
//                    type = envelope.type,
//                    datacontenttype = envelope.datacontenttype,
//                    specversion = envelope.specversion,
//                    source = envelope.source,
//                    time = envelope.time
//                )
//            }.first()
//        }
//    }
//}

/**
 * Extension function to map a Flow of inputs to a Flow of Envelopes containing the results.
 *
 * @param id A function to generate an ID for each input. Defaults to a random UUID.
 * @return A Flow emitting Envelopes containing the results.
 */
inline fun <T, reified R> F2Function<T, R>.mapToEnvelope(
    crossinline id: (T) -> String = { uuid4().toString() },
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
 * Extension function to map a Flow of inputs to a Flow of Envelopes containing the inputs.
 *
 * @param id A function to generate an ID for each input. Defaults to a random UUID.
 * @return A Flow emitting Envelopes containing the inputs.
 */
inline fun <reified T> Flow<T>.mapToEnvelope(
    crossinline id: (T) -> String = { uuid4().toString() },
): Flow<Envelope<T>> = map { input ->
    input.asEnvelope(
        id = id(input),
    )
}

/**
 * Extension function to map a Flow of inputs to a Flow of Envelopes containing the inputs.
 *
 * @param id A function to generate an ID for each input. Defaults to a random UUID.
 * @return A Flow emitting Envelopes containing the inputs.
 */
fun <T> Flow<T>.mapToEnvelope(
    type: String,
    id: (T) -> String = { uuid4().toString() },
): Flow<Envelope<T>> = map { input ->
    input.asEnvelopeWithType(
        id = id(input),
        type = type
    )
}

suspend inline fun <T, reified R> Envelope<T>.mapEnvelope(
    crossinline transform: suspend (value: T) -> R,
    source: String? = null,
    id: String = this.id,
    type: String =  R::class.simpleName ?: "Unknown",
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
    return transform(data).asEnvelopeWithType(type,this, source, id, time, datacontenttype)
}



/**
 * Extension function to map a Flow of inputs to a Flow of Envelopes containing the inputs.
 *
 * @param id A function to generate an ID for each input. Defaults to a random UUID.
 * @return A Flow emitting Envelopes containing the inputs.
 */
inline fun <T, reified R> Flow<Envelope<T>>.mapEnvelopesReified(
    crossinline transform: suspend (value: T) -> R
): Flow<Envelope<R>> = map { input ->
    input.mapEnvelope(transform)
}

/**
 * Extension function to map a Flow of inputs to a Flow of Envelopes containing the inputs.
 *
 * @param id A function to generate an ID for each input. Defaults to a random UUID.
 * @return A Flow emitting Envelopes containing the inputs.
 */
fun <T, R> Flow<Envelope<T>>.mapEnvelopes(
    transform: suspend (value: T) -> R,
    type: String
): Flow<Envelope<R>> = map { input ->
    input.mapEnvelopeWithType(transform, type)
}
