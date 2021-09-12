package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

expect interface F2Supplier<R>

typealias F2LambdaSupplier<T> = suspend () -> Flow<T>
