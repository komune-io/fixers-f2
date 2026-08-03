package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

/**
 * JVM actual: extends [F2LambdaSupplier] so every F2Supplier is a kotlin.jvm.functions.Function0,
 * keeping assignability to `() -> Flow<R>` and Kotlin-type detection by Spring.
 */
actual fun interface F2Supplier<R> : F2LambdaSupplier<R> {
    actual override operator fun invoke(): Flow<R>
}
