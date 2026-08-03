package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a function that transforms a Flow of type T to a Flow of type R.
 */
typealias F2LambdaFunction<T, R> = (Flow<T>) -> Flow<R>

/**
 * Fun interface representing a function that transforms a Flow of type T to a Flow of type R.
 *
 * Intentionally does NOT extend [F2LambdaFunction]: Kotlin/JS prohibits an interface from
 * implementing a (non-suspend) function type, and this module compiles to JS.
 */
@JsExport
fun interface F2Function<in T, out R> {
    /**
     * Invokes the function with the given Flow of type T and returns a Flow of type R.
     *
     * @param msgs The Flow of type T to be transformed.
     * @return The transformed Flow of type R.
     */
    operator fun invoke(msgs: Flow<T>): Flow<R>
}
