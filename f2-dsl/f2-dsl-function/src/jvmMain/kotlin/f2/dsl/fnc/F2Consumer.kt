package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual fun interface F2Consumer<T> {
	suspend operator fun invoke(unit: Flow<T>)
}
