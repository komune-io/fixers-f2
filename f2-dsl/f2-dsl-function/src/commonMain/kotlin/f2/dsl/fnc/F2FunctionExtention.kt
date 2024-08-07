package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

/**
 * Extension function to invoke an F2Function with a single input value.
 *
 * @param item The input value of type T.
 * @return The result of type R.
 */
suspend operator fun <T, R> F2Function<T, R>.invoke(item: T): R {
    return invoke(flowOf(item)).first()
}

/**
 * Extension function to invoke an F2Function with the receiver as the input value.
 *
 * @param fnc The F2Function to invoke.
 * @return The result of type R.
 */
suspend fun <T, R> T.invokeWith(fnc: F2Function<T, R>): R {
    return fnc.invoke(this)
}

/**
 * Creates an F2Function from a suspend function.
 *
 * @param fnc The suspend function to convert.
 * @return An F2Function that wraps the suspend function.
 */
fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R> = F2Function { msg ->
    msg.map { value ->
        fnc(value)
    }
}

/**
 * Creates an F2SupplierSingle from a suspend function.
 *
 * @param fnc The suspend function to convert.
 * @return An F2SupplierSingle that wraps the suspend function.
 */
fun <R> f2SupplierSingle(fnc: suspend () -> R): F2SupplierSingle<R> = F2SupplierSingle {
    fnc()
}

/**
 * Creates an F2Supplier from a suspend function that returns a Flow.
 *
 * @param fnc The suspend function to convert.
 * @return An F2Supplier that wraps the suspend function.
 */
fun <R> f2Supplier(fnc: suspend () -> Flow<R>): F2Supplier<R> = F2Supplier {
    fnc()
}

/**
 * Extension function to convert an Iterable to an F2Supplier.
 *
 * @return An F2Supplier that provides the elements of the Iterable as a Flow.
 */
fun <R> Iterable<R>.asF2Supplier(): F2Supplier<R> = F2Supplier {
    asFlow()
}

/**
 * Creates an F2Consumer from a suspend function.
 *
 * @param fnc The suspend function to convert.
 * @return An F2Consumer that wraps the suspend function.
 */
fun <R> f2Consumer(fnc: suspend (R) -> Unit): F2Consumer<R> = F2Consumer { flow ->
    flow.map(fnc).collect()
}
