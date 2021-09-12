package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

expect interface F2Consumer<T>

typealias F2LambdaConsumer<T> = suspend (Flow<T>) -> Unit
