package f2.dsl

import kotlinx.coroutines.flow.Flow

expect interface F2FunctionRemote<T, R>
expect interface F2SupplierRemote<T>
expect interface F2ConsumerRemote<T>

typealias F2Function<T, R> = suspend (Flow<T>) -> Flow<R>
typealias F2Supplier<T> = suspend () -> Flow<T>
typealias F2Consumer<T> = suspend (Flow<T>) -> Unit