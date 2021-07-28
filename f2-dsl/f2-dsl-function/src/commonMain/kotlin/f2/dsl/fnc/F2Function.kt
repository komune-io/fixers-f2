package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

expect interface F2Function<T, R>

typealias F2LambdaFunction<T, R> = suspend (Flow<T>) -> Flow<R>