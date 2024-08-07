package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

/**
 * Type alias for a suspend function that supplies a Flow of type R.
 */
typealias F2LambdaSupplier<R> = suspend () -> Flow<R>

/**
 * Expect fun interface representing a supplier of a Flow of type R.
 */
expect fun interface F2Supplier<R> : F2LambdaSupplier<R> {
    /**
     * Invokes the supplier to get a Flow of type R.
     *
     * @return The Flow of type R.
     */
    override suspend operator fun invoke(): Flow<R>
}

/**
 * Type alias for a suspend function that supplies a single value of type R.
 */
typealias F2LambdaSupplierSingle<R> = suspend () -> R

/**
 * Expect fun interface representing a supplier of a single value of type R.
 */
expect fun interface F2SupplierSingle<R> : F2LambdaSupplierSingle<R> {
    /**
     * Invokes the supplier to get a single value of type R.
     *
     * @return The single value of type R.
     */
    override suspend operator fun invoke(): R
}
