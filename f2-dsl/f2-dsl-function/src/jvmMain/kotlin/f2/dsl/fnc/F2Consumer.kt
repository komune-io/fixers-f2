package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual fun interface F2Consumer<T>: suspend (Flow<T>) -> Unit {
	override suspend operator fun invoke(msg: Flow<T>)
}
