package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual fun interface F2Supplier<R> : suspend () -> Flow<R> {
	override suspend operator fun invoke(): Flow<R>
}
