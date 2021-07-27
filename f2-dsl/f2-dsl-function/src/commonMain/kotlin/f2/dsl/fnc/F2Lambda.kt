package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

typealias F2Consumer<T> = suspend (Flow<T>) -> Unit
typealias F2Function<T, R> = suspend (Flow<T>) -> Flow<R>
typealias F2Supplier<T> = suspend () -> Flow<T>
