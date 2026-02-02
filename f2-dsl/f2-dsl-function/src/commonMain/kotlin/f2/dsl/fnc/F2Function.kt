package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a suspend function that transforms a Flow of type T to a Flow of type R.
 */
typealias F2LambdaFunction<T, R> = suspend (Flow<T>) -> Flow<R>

/**
 * Fun interface representing a function that transforms a Flow of type T to a Flow of type R.
 */
@JsExport
fun interface F2Function<in T, out R> : F2LambdaFunction<T, R> {
    /**
     * Invokes the function with the given Flow of type T and returns a Flow of type R.
     *
     * @param msgs The Flow of type T to be transformed.
     * @return The transformed Flow of type R.
     */
    override suspend operator fun invoke(msgs: Flow<T>): Flow<R>
}
