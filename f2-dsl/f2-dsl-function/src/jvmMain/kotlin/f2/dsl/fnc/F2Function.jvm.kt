package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

/**
 * JVM actual: extends [F2LambdaFunction] so every F2Function is a kotlin.jvm.functions.Function1,
 * keeping assignability to `(Flow<T>) -> Flow<R>` and Kotlin-type detection by Spring.
 */
actual fun interface F2Function<in T, out R> : F2LambdaFunction<T, R> {
    actual override operator fun invoke(msgs: Flow<T>): Flow<R>
}
