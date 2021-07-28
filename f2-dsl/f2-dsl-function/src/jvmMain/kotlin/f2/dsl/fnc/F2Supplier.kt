package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual fun interface F2Supplier<R> {
	suspend operator fun invoke(): Flow<R>
}
