package f2.dsl.fnc.operators

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * Extension function to group elements of a Flow by a specified key.
 *
 * @param keySelector A function to extract the key for each element.
 * @return A Flow emitting pairs of keys and corresponding flows of elements.
 */
fun <T, K> Flow<T>.groupBy(keySelector: (T) -> K): Flow<Pair<K, Flow<T>>> = channelFlow {
    val groups = mutableMapOf<K, Channel<T>>()
    // Launch a coroutine to collect the original flow
    launch {
        collect { value ->
            val key = keySelector(value)
            val groupChannel = groups.getOrPut(key) {
                Channel<T>(Channel.BUFFERED).also { channel ->
                    // For each new group, send a new flow to the downstream collector
                    launch {
                        send(key to channel.consumeAsFlow())
                    }
                }
            }
            groupChannel.send(value)
        }
        // Close all channels after the original flow collection is complete
        groups.values.forEach { it.close() }
    }
}
