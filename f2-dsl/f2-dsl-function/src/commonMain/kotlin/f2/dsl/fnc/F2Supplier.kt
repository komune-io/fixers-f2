package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a function that supplies a Flow of type R.
 */
typealias F2LambdaSupplier<R> = () -> Flow<R>

/**
 * Fun interface representing a supplier of a Flow of type R.
 *
 * Intentionally does NOT extend [F2LambdaSupplier]: Kotlin/JS prohibits an interface from
 * implementing a (non-suspend) function type, and this module compiles to JS.
 */
@JsExport
fun interface F2Supplier<R> {
    /**
     * Invokes the supplier to get a Flow of type R.
     *
     * @return The Flow of type R.
     */
    operator fun invoke(): Flow<R>
}

/**
 * Type alias for a suspend function that supplies a single value of type R.
 */
typealias F2LambdaSupplierSingle<R> = suspend () -> R

/**
 * Fun interface representing a supplier of a single value of type R.
 */
@JsExport
fun interface F2SupplierSingle<R> : F2LambdaSupplierSingle<R> {
    /**
     * Invokes the supplier to get a single value of type R.
     *
     * @return The single value of type R.
     */
    override suspend operator fun invoke(): R
}
