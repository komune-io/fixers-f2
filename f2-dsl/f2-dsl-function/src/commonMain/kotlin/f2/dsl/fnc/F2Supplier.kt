package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a suspend function that supplies a Flow of type R.
 */
typealias F2LambdaSupplier<R> = suspend () -> Flow<R>

/**
 * Fun interface representing a supplier of a Flow of type R.
 */
@JsExport
// The suspend function supertype has no JS export, by design: https://youtrack.jetbrains.com/issue/KT-83009
@Suppress("EXPOSED_NOT_EXPORTED_SUPER_INTERFACE_WARNING")
fun interface F2Supplier<R> : F2LambdaSupplier<R> {
    /**
     * Invokes the supplier to get a Flow of type R.
     *
     * @return The Flow of type R.
     */
    @JsExport.Ignore
    // The F2 wire shape: suspend to open the call (an HTTP request, a bean lookup), then a Flow of its results.
    @Suppress("SuspendFunWithFlowReturnType")
    override suspend operator fun invoke(): Flow<R>
}

/**
 * Type alias for a suspend function that supplies a single value of type R.
 */
typealias F2LambdaSupplierSingle<R> = suspend () -> R

/**
 * Fun interface representing a supplier of a single value of type R.
 */
@JsExport
// The suspend function supertype has no JS export, by design: https://youtrack.jetbrains.com/issue/KT-83009
@Suppress("EXPOSED_NOT_EXPORTED_SUPER_INTERFACE_WARNING")
fun interface F2SupplierSingle<R> : F2LambdaSupplierSingle<R> {
    /**
     * Invokes the supplier to get a single value of type R.
     *
     * @return The single value of type R.
     */
    @JsExport.Ignore
    override suspend operator fun invoke(): R
}
