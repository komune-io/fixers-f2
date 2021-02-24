package f2.dsl

import kotlinx.coroutines.flow.Flow

expect interface F2Remote<T, R>

typealias F2Flow<T, R> = suspend (Flow<T>) -> Flow<R>