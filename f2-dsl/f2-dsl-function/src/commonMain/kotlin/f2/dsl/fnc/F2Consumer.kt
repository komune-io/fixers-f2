package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

typealias F2LambdaConsumer<T> = suspend (Flow<T>) -> Unit

expect fun interface F2Consumer<T>: F2LambdaConsumer<T> {
    override suspend operator fun invoke(msg: Flow<T>)
}
