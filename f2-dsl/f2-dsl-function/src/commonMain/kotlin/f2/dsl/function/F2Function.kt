package f2.dsl.function

import kotlinx.coroutines.flow.Flow

typealias F2Function<T, R> = suspend (Flow<T>) -> Flow<R>

expect interface F2FunctionRemote<T, R>