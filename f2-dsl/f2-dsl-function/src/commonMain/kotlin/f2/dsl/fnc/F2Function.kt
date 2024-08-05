package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

typealias F2LambdaFunction<T, R> = suspend (Flow<T>) -> Flow<R>

expect fun interface F2Function<in T, out R>: F2LambdaFunction<T, R> {
    override suspend operator fun invoke(msgs: Flow<T>): Flow<R>
}
