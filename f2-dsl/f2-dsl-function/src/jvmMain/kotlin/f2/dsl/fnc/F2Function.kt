package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual fun interface F2Function<T, R> {
	suspend operator fun invoke(msg: Flow<T>): Flow<R>
}
