package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow

actual interface F2FunctionDeclaration<T, R> {
	suspend fun invoke(msg: Flow<T>): Flow<R>
}

actual interface F2SupplierDeclaration<T> {
	suspend fun invoke(): Flow<T>
}

actual interface F2ConsumerDeclaration<R> {
	suspend fun invoke(unit: Flow<R>)
}
