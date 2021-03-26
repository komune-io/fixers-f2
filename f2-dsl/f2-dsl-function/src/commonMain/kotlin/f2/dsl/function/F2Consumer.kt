package f2.dsl.function

import kotlinx.coroutines.flow.Flow

expect interface F2ConsumerRemote<T>
typealias F2Consumer<T> = suspend (Flow<T>) -> Unit