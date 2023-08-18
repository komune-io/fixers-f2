package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual fun interface F2Function<in T, out R>: suspend (Flow<T>) -> Flow<R> {
	override suspend operator fun invoke(msgs: Flow<T>): Flow<R>
}
