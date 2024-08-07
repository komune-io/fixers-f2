package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a suspend function that consumes a Flow of type T.
 */
typealias F2LambdaConsumer<T> = suspend (Flow<T>) -> Unit

/**
 * Expect fun interface representing a consumer of a Flow of type T.
 */
expect fun interface F2Consumer<T> : F2LambdaConsumer<T> {
    /**
     * Invokes the consumer with the given Flow of type T.
     *
     * @param msg The Flow of type T to be consumed.
     */
    override suspend operator fun invoke(msg: Flow<T>)
}
