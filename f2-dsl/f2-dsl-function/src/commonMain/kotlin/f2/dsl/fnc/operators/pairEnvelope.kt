package f2.dsl.fnc.operators

import f2.dsl.cqrs.envelope.Envelope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow

/**
 * Extension function to pair elements of a Flow of Envelopes with their corresponding results.
 *
 * @param T The type of the enveloped commands.
 * @param R The type of the enveloped results.
 * @param chunk The size of the chunks the flow is executed by.
 * @param execute A function to execute on the chunked flow of Envelopes.
 * @return A Flow emitting pairs of original data and corresponding results.
 */
inline fun <reified T, R> Flow<Envelope<T>>.pairEnvelope(
    chunk: Int = CHUNK_DEFAULT_SIZE,
    crossinline execute: suspend (Flow<Envelope<T>>) -> Flow<Envelope<R>>
): Flow<Pair<T, R>> = channelFlow {
    chunk(chunk).collect { chunked ->
        val commandMap: Map<String, Envelope<T>> = chunked.associateBy { it.id }

        val envelopedCommands = commandMap.values.asFlow()
        val responseFlow = execute(envelopedCommands)

        responseFlow.collect { response: Envelope<R> ->
            val originalCommandEnvelope: Envelope<T> = checkNotNull(commandMap[response.id]) {
                "No matching command found for envelope ID: ${response.id}"
            }
            val originalCommand = originalCommandEnvelope.data
            val responseData = response.data
            send(originalCommand to responseData)
        }
    }
}
